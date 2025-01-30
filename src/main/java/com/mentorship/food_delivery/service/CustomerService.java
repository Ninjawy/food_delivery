package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.dto.CustomerDTO;
import com.mentorship.food_delivery.dto.LoginRequest;
import com.mentorship.food_delivery.dto.LoginResponse;
import com.mentorship.food_delivery.dto.RegisterCustomer;
import com.mentorship.food_delivery.utils.exception.authentication.InvalidPasswordException;
import com.mentorship.food_delivery.utils.exception.resources.ResourceAlreadyExistsException;
import com.mentorship.food_delivery.utils.exception.resources.ResourceNotFoundException;
import com.mentorship.food_delivery.utils.mappers.CustomerMapper;
import com.mentorship.food_delivery.model.customer.Customer;
import com.mentorship.food_delivery.repository.customer.CustomerRepository;
import com.mentorship.food_delivery.model.other.Role;
import com.mentorship.food_delivery.model.user.RoleEnum;
import com.mentorship.food_delivery.model.user.UserType;
import com.mentorship.food_delivery.model.security.JwtClaims;
import com.mentorship.food_delivery.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.mentorship.food_delivery.utils.StringUtils.normalizeString;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTypeService userTypeService;
    private final JwtService jwtService;
    private final RoleTypeService roleTypeService;

    public CustomerDTO registerCustomer(RegisterCustomer registerCustomer) {
        if (isCustomerExists(registerCustomer.getUsername()))
            throw new ResourceAlreadyExistsException("Username already exists");

        String passwordEncrypted = passwordEncoder.encode(registerCustomer.getPassword());
        UserType userType = userTypeService.getCustomerUserType();

        List<Role> roles = roleTypeService.findRoles(userType);

        Customer newCustomer = Customer.create(registerCustomer, passwordEncrypted, userType, roles);
        Customer savedCustomer = customerRepository.save(newCustomer);

        return CustomerMapper.toDto(savedCustomer);
    }

    public boolean isCustomerExists(String customerUsername) {
        String username = normalizeString(customerUsername);
        return customerRepository.existsByUserUsername(username);
    }

    public Page<CustomerDTO> getCustomers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(CustomerMapper::toDto);
    }

    public Customer findCustomer(String username) {
        return customerRepository.findOptionalByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public Customer findCustomer(Principal principal) {
        return findCustomer(principal.getName());
    }

    public CustomerDTO findCustomerDTO(Principal principal) {
        Customer customer = findCustomer(principal.getName());
        return CustomerMapper.toDto(customer);
    }


    public LoginResponse<CustomerDTO> login(LoginRequest loginRequest) {
        Customer customer = findCustomer(loginRequest.getUsername());
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), customer.getUser().getPassword());

        if (!passwordMatches)
            throw new InvalidPasswordException("Invalid password");

        CustomerDTO customerDTO = CustomerMapper.toDto(customer);

        String token = generateJwtToken(customer);

        Instant tokenExpirationDate = jwtService.getTokenExpirationDate()
                .toInstant();

        return LoginResponse.create(customerDTO, token, tokenExpirationDate);
    }

    private String generateJwtToken(Customer customer) {
        List<String> roles = roleTypeService.findRolesInString(RoleEnum.CUSTOMER);
        Map<String, ?> customerClaims = Map.of(JwtClaims.ROLES.name(), roles);
        return "Bearer ".concat(jwtService.generateToken(customerClaims, customer.getUser()));
    }
}

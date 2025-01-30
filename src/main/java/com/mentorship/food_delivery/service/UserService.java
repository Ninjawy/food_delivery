package com.mentorship.food_delivery.service;

import com.mentorship.food_delivery.dto.RegisterUser;
import com.mentorship.food_delivery.dto.UserDTO;
import com.mentorship.food_delivery.utils.exception.resources.ResourceAlreadyExistsException;
import com.mentorship.food_delivery.utils.exception.resources.ResourceNotFoundException;
import com.mentorship.food_delivery.utils.mappers.UserMapper;
import com.mentorship.food_delivery.model.other.Role;
import com.mentorship.food_delivery.model.user.User;
import com.mentorship.food_delivery.model.user.UserType;
import com.mentorship.food_delivery.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public final class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserTypeService userTypeService;
    private final RoleTypeService roleTypeService;

    public UserDTO registerUser(RegisterUser registerUser) {
        UserType userType = userTypeService.findByName(registerUser.getUserType());
        User addedUser = addNewUser(registerUser, userType);
        return UserMapper.toDto(addedUser);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username.toLowerCase(Locale.ROOT));
    }

    private User addNewUser(RegisterUser registerUser, UserType userType) {
        if (existsByUsername(registerUser.getUsername()))
            throw new ResourceAlreadyExistsException("User already exists");

        String encryptedPassword = passwordEncoder.encode(registerUser.getPassword());

        List<Role> roles = roleTypeService.findRoles(userType);

        User newUser = User.create(registerUser, encryptedPassword, userType, roles);

        return userRepository.save(newUser);
    }

    public User findUserEntity(String username) {
        return userRepository.findOptionalUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User validateUserEntity(String username, String password) {
        return userRepository.findOptionalUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDTO findUserDto(Principal principal) {
        User foundUser = findUserEntity(principal.getName());
        return UserMapper.toDto(foundUser);
    }
}

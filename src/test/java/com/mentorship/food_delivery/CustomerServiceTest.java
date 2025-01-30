package com.mentorship.food_delivery;

import com.mentorship.food_delivery.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


//@AutoConfigureMockMvc
//@SpringBootTest
//@Testcontainers
//@ExtendWith(SpringExtension.class)
class CustomerServiceTest extends AbstractContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    void shouldSaveAndCustomer() {
//		Iterable<CustomerDTO> customers = customerService.getAllCustomers();
//
//        for (CustomerDTO customerDTO : CustomerDataset.customerListDto) {
//        }
    }

    @Test
    void getAllCustomers() {

    }
}

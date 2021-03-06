package nl.agility.customer.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.agility.commons.web.config.SecurityAutoConfiguration;
import nl.agility.customer.CustomerMother;
import nl.agility.customer.domain.Customer;
import nl.agility.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static nl.agility.customer.ui.BaseController.CUSTOMER_V1;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
@ImportAutoConfiguration(SecurityAutoConfiguration.class)
class CustomerControllerTest {

    static final String CUSTOMERS_URI = "/api/customers";
    static final String CUSTOMER_INVALID_VERSION = "application/vnd.customer.api.vX+json";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        when(customerService.retrieveCustomers()).thenReturn(List.of(CustomerMother.complete().build()));
    }

    @Test
    void retrieveCustomersWithV1AcceptHeaderProducesValidResponse() throws Exception {
        mockMvc
            .perform(get(CUSTOMERS_URI)
            .accept(CUSTOMER_V1))
            .andExpect(status().isOk())
            .andExpect(content().string(is(notNullValue())))
            .andExpect(jsonPath("$[0].name", is("Robert C. Martin")));

        verify(customerService, times(1)).retrieveCustomers();
    }

    @Test
    void retrieveCustomersWithoutAnAcceptHeaderProducesValidV1Response() throws Exception {
        mockMvc
            .perform(get(CUSTOMERS_URI))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CUSTOMER_V1))
            .andExpect(content().string(is(notNullValue())))
            .andExpect(jsonPath("$[0].name", is("Robert C. Martin")));

        verify(customerService, times(1)).retrieveCustomers();
    }

    @Test
    void retrieveCustomersWithInvalidAcceptHeaderProducesErrorResponse() throws Exception {
        mockMvc
            .perform(get(CUSTOMERS_URI)
            .accept(CUSTOMER_INVALID_VERSION))
            .andExpect(status().isNotAcceptable())
            .andExpect(content().string(is(emptyString())));

        verify(customerService, times(0)).retrieveCustomers();
    }

    @Test
    @Disabled
    void retrieveInvalidCustomersProducesErrorResponse() throws Exception {
        Customer customer = CustomerMother.complete()
            .name(null)
            .build();

        when(customerService.retrieveCustomers()).thenReturn(List.of(customer));

        mockMvc
            .perform(get(CUSTOMERS_URI)
            .accept(CUSTOMER_V1))
            .andExpect(status().isBadRequest());

        verify(customerService, times(1)).retrieveCustomers();
    }

}

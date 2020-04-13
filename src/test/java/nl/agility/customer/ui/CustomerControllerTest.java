package nl.agility.customer.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.agility.commons.web.config.SecurityAutoConfiguration;
import nl.agility.customer.domain.Customer;
import nl.agility.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private static final String CUSTOMERS_URI = "/api/customers";
    private static final String CUSTOMER_INVALID_VERSION = "application/vnd.customer.api.vX+json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void retrieveCustomersWithV1AcceptHeaderProducesValidResponse() throws Exception {
        when(customerService.retrieveCustomers()).thenReturn(getCustomers());

        mockMvc
            .perform(get(CUSTOMERS_URI)
            .accept(CUSTOMER_V1))
            .andExpect(status().isOk())
            .andExpect(content().string(is(notNullValue())))
            .andExpect(jsonPath("$[0].name", is("Pino Lino")));

        verify(customerService, times(1)).retrieveCustomers();
    }

    @Test
    void retrieveCustomersWithoutAnAcceptHeaderProducesValidV1Response() throws Exception {
        when(customerService.retrieveCustomers()).thenReturn(getCustomers());

        mockMvc
            .perform(get(CUSTOMERS_URI))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CUSTOMER_V1))
            .andExpect(content().string(is(notNullValue())))
            .andExpect(jsonPath("$[0].name", is("Pino Lino")));

        verify(customerService, times(1)).retrieveCustomers();
    }

    @Test
    void retrieveCustomersWithInvalidAcceptHeaderProducesErrorResponse() throws Exception {
        when(customerService.retrieveCustomers()).thenReturn(getCustomers());

        mockMvc
            .perform(get(CUSTOMERS_URI)
            .accept(CUSTOMER_INVALID_VERSION))
            .andExpect(status().isNotAcceptable())
            .andExpect(content().string(is(emptyString())));

        verify(customerService, times(0)).retrieveCustomers();
    }

    List<Customer> getCustomers() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setVersion(null);
        customer.setCreated(LocalDate.now());
        customer.setLastUpdated(null);
        customer.setName("Pino Lino");

        List<Customer> customers = new ArrayList<>();

        return List.of(customer);
    }

}

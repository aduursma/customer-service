package nl.agility.customer.service;

import nl.agility.customer.CustomerMother;
import nl.agility.customer.domain.Customer;
import nl.agility.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        when(customerRepository.findAll()).thenReturn(List.of(CustomerMother.complete().build()));
    }

    @Test
    void canRetrieveAListOfCustomers() {
        List<Customer> customers = customerService.retrieveCustomers();

        assertThat(customers, is(notNullValue()));
        assertThat(customers.size(), equalTo(1));
    }

}

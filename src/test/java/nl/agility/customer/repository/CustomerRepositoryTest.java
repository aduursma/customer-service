package nl.agility.customer.repository;

import nl.agility.customer.config.JpaConfig;
import nl.agility.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void findAllShouldRetrieveAllCustomers() {
        List<Customer> customers = repository.findAll();

        assertThat(customers, is(notNullValue()));
        assertThat(customers.size(), equalTo(5));
    }

    @Test
    void saveCustomerShouldAddAuditingTimestamps() {
        Customer customer = new Customer();
        customer.setName("Linus Torvalds");

        Customer savedCustomer = repository.save(customer);

        assertThat(savedCustomer, is(notNullValue()));
        assertThat(savedCustomer.getId(), is(notNullValue()));
        assertThat(savedCustomer.getVersion(), equalTo(0));
        assertThat(savedCustomer.getCreated(), is(notNullValue()));
        assertThat(savedCustomer.getLastUpdated(), is(notNullValue()));
        assertThat(savedCustomer.getName(), equalTo("Linus Torvalds"));
    }

}

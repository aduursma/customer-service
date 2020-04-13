package nl.agility.customer.repository;

import nl.agility.customer.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.jpa.properties.hibernate.format_sql=true"
})
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void doTest() {
        List<Customer> customers = repository.findAll();

        assertThat(customers, is(notNullValue()));
        assertThat(customers.size(), equalTo(5));
    }

}

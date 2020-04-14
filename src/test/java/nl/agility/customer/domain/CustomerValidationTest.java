package nl.agility.customer.domain;

import nl.agility.customer.CustomerTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class CustomerValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validatingAValidInstanceThrowsNoExceptions() {
        Customer customer = CustomerTestUtils.getCustomers().get(0);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations, is(notNullValue()));
        assertThat(violations.size(), equalTo(0));
    }

    @Test
    void validatingAnIValidInstanceThrowsAnException() {
        Customer customer = CustomerTestUtils.getCustomers().get(0);
        customer.setName(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations, is(notNullValue()));
        assertThat(violations.size(), equalTo(1));

        ConstraintViolation<Customer> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString(), equalTo("name"));
        assertThat(violation.getMessage(), equalTo("must not be blank"));

    }

}

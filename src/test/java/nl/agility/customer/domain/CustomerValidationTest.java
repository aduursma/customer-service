package nl.agility.customer.domain;

import nl.agility.customer.CustomerMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validatingAValidInstanceThrowsNoExceptions() {
        Customer customer = CustomerMother.complete().build();

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations, is(notNullValue()));
        assertThat(violations.size(), equalTo(0));
    }

    @Test
    void validatingAnIValidInstanceThrowsAnException() {
        Customer customer = CustomerMother.complete().build();
        customer.setName(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertThat(violations, is(notNullValue()));
        assertThat(violations.size(), equalTo(1));

        ConstraintViolation<Customer> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString(), equalTo("name"));
        assertThat(violation.getMessage(), equalTo("must not be blank"));

    }

}

package nl.agility.customer.domain;

import com.google.common.testing.EqualsTester;
import nl.agility.customer.CustomerMother;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("ALL")
class CustomerTest {

    EqualsTester equalsTester;

    @BeforeEach
    void setUp() {
        equalsTester = new EqualsTester();
    }

    @Test
    void customersWithDifferentIdsAreNotEqual() {
        Customer customerOne = CustomerMother.complete().build();
        Customer customerTwo = CustomerMother.complete().id(2L).build();

        equalsTester
            .addEqualityGroup(customerOne)
            .addEqualityGroup(customerTwo)
            .testEquals();
    }

    @Test
    void verifyAllPropertiesArePartOfToString() throws Exception {
        Customer customer = CustomerMother.complete().build();

        for (Field field : FieldUtils.getAllFieldsList(customer.getClass())) {
            var name = field.getName();
            var value = FieldUtils.readField(field, customer, true);

            assertThat(customer.toString(), containsString(String.format("%s=%s", name, value)));
        }
    }

}

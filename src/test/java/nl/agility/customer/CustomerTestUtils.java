package nl.agility.customer;

import nl.agility.customer.domain.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class CustomerTestUtils {

    private CustomerTestUtils() {
    }

    public static List<Customer> getCustomers() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setVersion(1);
        customer.setCreated(LocalDateTime.parse("2020-04-10T01:02:03.123456789"));
        customer.setLastUpdated(LocalDateTime.parse("2020-04-12T21:40:19.987654321"));
        customer.setName("Robert C. Martin");

        List<Customer> customers = new ArrayList<>();

        return List.of(customer);
    }

}

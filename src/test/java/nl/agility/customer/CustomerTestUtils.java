package nl.agility.customer;

import nl.agility.customer.domain.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class CustomerTestUtils {

    private CustomerTestUtils() {
    }

    public static List<Customer> getCustomers() {
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

package nl.agility.customer;

import nl.agility.customer.domain.Customer;

import java.time.LocalDateTime;

public class CustomerMother {

    public static Customer.CustomerBuilder complete() {
        return Customer.builder()
            .id(1L)
            .version(0)
            .created(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .name("Robert C. Martin");
    }

}

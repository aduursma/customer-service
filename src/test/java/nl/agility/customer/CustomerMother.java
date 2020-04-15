package nl.agility.customer;

import java.time.LocalDateTime;

public class CustomerMother {

    public static CustomerBuilder.CustomerBuilder complete() {
        return CustomerBuilder.builder()
            .id(1L)
            .version(0)
            .created(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .name("Robert C. Martin");
    }

}

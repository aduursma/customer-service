package nl.agility.customer;

import lombok.Builder;
import nl.agility.customer.domain.Customer;

import java.time.LocalDateTime;

public class CustomerBuilder extends Customer {

    @Builder(builderClassName = "CustomerBuilder")
    public CustomerBuilder(Long id, int version, LocalDateTime created, LocalDateTime lastUpdated, String name) {
        setId(id);
        setVersion(version);
        setCreated(created);
        setLastUpdated(lastUpdated);
        setName(name);
    }

}

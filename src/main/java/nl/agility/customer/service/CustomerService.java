package nl.agility.customer.service;

import lombok.RequiredArgsConstructor;
import nl.agility.customer.domain.Customer;
import nl.agility.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> retrieveCustomers() {
        return this.customerRepository.findAll();
    }

}

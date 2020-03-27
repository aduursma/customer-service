package nl.agility.customer.ui;

import lombok.RequiredArgsConstructor;
import nl.agility.customer.domain.Customer;
import nl.agility.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> retrieveCustomers() {
        return this.customerService.retrieveCustomers();
    }

}

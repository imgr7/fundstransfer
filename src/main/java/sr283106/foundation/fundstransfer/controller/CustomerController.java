package sr283106.foundation.fundstransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sr283106.foundation.fundstransfer.domain.Customer;
import sr283106.foundation.fundstransfer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers/")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping("all")
    ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Entering into getAllCustomers");
        return ResponseEntity.ok(customerService.getAllCustomerDetails());
    }

    @GetMapping("{customerId}")
    ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        logger.info("Entering into getCustomerById");
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PutMapping("{customerId}")
    ResponseEntity<Customer> updateCustomerById(@PathVariable Long customerId,@RequestBody Customer customer) {
        logger.info("Entering into updateCustomerById");
        return ResponseEntity.ok(customerService.updateCustomerById(customerId, customer));
    }

    @PostMapping
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("Entering into createCustomer");
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @DeleteMapping("{customerId}")
    ResponseEntity<String> deleteCustomerById(@PathVariable Long customerId) {
        logger.info("Entering into deleteCustomerById");
        customerService.deleteCustomerById(customerId);
        logger.info("Exiting from deleteCustomerById");
        return ResponseEntity.ok("Record Is successfully deleted");
    }
}

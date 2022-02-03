package sr283106.foundation.fundstransfer.service;

import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomerDetails();

    Customer getCustomerById(Long customerId);

    Customer updateCustomerById(Long customerId,Customer customer);

    Customer createCustomer(Customer customer);

    void deleteCustomerById(Long customerId);

}

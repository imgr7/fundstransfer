package sr283106.foundation.fundstransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.dao.AccountDAO;
import sr283106.foundation.fundstransfer.dao.CustomerDAO;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.domain.Customer;
import sr283106.foundation.fundstransfer.exception.CustomerNotFoundException;
import sr283106.foundation.fundstransfer.exception.DuplicateAccountException;
import sr283106.foundation.fundstransfer.service.CustomerService;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Customer> getAllCustomerDetails() {
        return customerDAO.findAll();
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerDAO.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with " + customerId + " Not Found"));
    }

    @Override
    public Customer updateCustomerById(Long customerId, Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateAccountDetails(customer.getAccount());
        return customerDAO.save(customer);
    }

    private void validateAccountDetails(Account account) {
        if (account.getAccountNumber()!=null && accountDAO.findById(account.getAccountNumber()).isPresent()) {
            throw new DuplicateAccountException(" Duplicate account found !!! Please check the account number properly");
        }
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerDAO.delete(customerDAO.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with " + customerId + " Not Found")));
    }
}

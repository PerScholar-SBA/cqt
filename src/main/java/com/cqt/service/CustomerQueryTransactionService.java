package com.cqt.service;

import com.cqt.models.Customer;
import com.cqt.models.CustomerQuery;
import com.cqt.models.CustomerQueryTransaction;
import com.cqt.models.Employee;
import com.cqt.repository.CustomerQueryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerQueryTransactionService {

    @Autowired
    private CustomerQueryTransactionRepository transactionRepository;

    public List<CustomerQueryTransaction> getCustomerQuerieTrasactions() {
        return transactionRepository.findAll();
    }

    public CustomerQueryTransaction getCustomerQueryTransactionById(Long id) {return transactionRepository.findById(id).orElse(null); }
    public CustomerQueryTransaction saveCustomerQueryTransaction(CustomerQueryTransaction customerQueryTransaction) {
        return transactionRepository.save(customerQueryTransaction);
    }

    public void deleteCustomerQueryTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    public CustomerQueryTransaction createTransaction(Customer customer, Employee employee, String status, String notes) {
        CustomerQueryTransaction transaction = new CustomerQueryTransaction();
        transaction.setCustomer(customer);
        transaction.setEmployee(employee);
        transaction.setStatus(status);
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setEmployeeNotes(notes);
        return transactionRepository.save(transaction);
    }

    // Other service methods as needed
}
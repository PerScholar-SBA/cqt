package com.cqt.service;

import com.cqt.models.Customer;
import com.cqt.models.CustomerQuery;
import com.cqt.repository.CustomerQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryService {
    @Autowired
    private CustomerQueryRepository customerQueryRepository;
    public List<CustomerQuery> getCustomerQueries() {
        return customerQueryRepository.findAll();
    }

    public CustomerQuery getCustomerQueryById(Long id) {return customerQueryRepository.findById(id).orElse(null); }
    public CustomerQuery saveCustomerQuery(CustomerQuery customerQuery) {
        return customerQueryRepository.save(customerQuery);
    }

    public void deleteCustomerQuery(Long id) {
        customerQueryRepository.deleteById(id);
    }
}

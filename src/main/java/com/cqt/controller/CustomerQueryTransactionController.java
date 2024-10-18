package com.cqt.controller;

import com.cqt.models.Customer;
import com.cqt.models.Employee;
import com.cqt.service.CustomerQueryTransactionService;
import com.cqt.service.CustomerService;
import com.cqt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cqtc")
public class CustomerQueryTransactionController {

    @Autowired
    private CustomerQueryTransactionService transactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public String getCustomerQuerieTrasactions(Model model) {
        List<Employee> employee = employeeService.getAllEmployees();  // Fetch employee from service
        model.addAttribute("employees", employee);  // Add employees to the model
        List<Customer> customer = customerService.getAllCustomers();  // Fetch employee from service
        model.addAttribute("customers", customer);  // Add employees to the model
        model.addAttribute("page","customers");
        return "cqtc/list";  // Return the Thymeleaf template
    }
    @PostMapping("/create")
    public String createTransaction(@RequestParam Long customerId, @RequestParam Long employeeId,
                                    @RequestParam String status, @RequestParam String notes) {
        // Fetch customer and employee by their IDs from their respective services
        Customer customer = customerService.getCustomerById(customerId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        transactionService.createTransaction(customer, employee, status, notes);

        return "redirect:/cqtc";
    }
}
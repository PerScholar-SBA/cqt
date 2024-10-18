package com.cqt.controller;

import com.cqt.models.Customer;
import com.cqt.models.Employee;
import com.cqt.repository.EmployeeRepository;
import com.cqt.service.CustomerService;
import com.cqt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customer = customerService.getAllCustomers();  // Fetch Customer from service
        model.addAttribute("customers", customer);  // Add customers to the model
        model.addAttribute("page","customers");
        return "customers/list";  // Return the Thymeleaf template
    }

    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/customer-form";
    }

    // Admin creating or editing a task
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById (id);
//        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("customer", customer);
//        model.addAttribute("employees", employees);  // Provide employees for task assignment
        return "customers/customer-form";  // View for creating or editing a task
    }
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }


    // Delete an employee (Admin-only)
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";  // Redirect back to employee list
    }
}
package com.cqt.controller;

import com.cqt.models.Customer;
import com.cqt.models.CustomerQuery;
import com.cqt.models.Product;
import com.cqt.service.CustomerQueryService;
import com.cqt.service.CustomerService;
import com.cqt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/customer-queries")
public class CustomerQueryController {

    @Autowired
    private CustomerQueryService customerQueryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    // Show list of all queries
    @GetMapping
    public String listCustomerQueries(Model model) {
        List<CustomerQuery> customerQueries = customerQueryService.getCustomerQueries();
        model.addAttribute("customerQueries", customerQueries);
        return "customer-query/list";  // View name for the list page
    }

    // Show form for creating a new query
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("customerQuery", new CustomerQuery());

        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();

        model.addAttribute("customers", customers);
        model.addAttribute("products", products);

        return "customer-query/customer-query-form";  // View name for the form page
    }

    // Save a new or edited query
    @PostMapping("/save")
    public String saveCustomerQuery(@ModelAttribute("customerQuery") CustomerQuery customerQuery) {
        customerQueryService.saveCustomerQuery(customerQuery);
        return "redirect:/customer-queries";  // Redirect to the list after saving
    }

    // Show form for editing an existing query
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CustomerQuery customerQuery = customerQueryService.getCustomerQueryById(id);
        model.addAttribute("customerQuery", customerQuery);

        List<Customer> customers = customerService.getAllCustomers();
        List<Product> products = productService.getAllProducts();

        model.addAttribute("customers", customers);
        model.addAttribute("products", products);

        return "customer-query/customer-query-form";  // View name for the form page
    }

    // Delete a query
    @GetMapping("/delete/{id}")
    public String deleteCustomerQuery(@PathVariable Long id) {
        customerQueryService.deleteCustomerQuery(id);
        return "redirect:/customer-queries";  // Redirect to the list after deletion
    }
}

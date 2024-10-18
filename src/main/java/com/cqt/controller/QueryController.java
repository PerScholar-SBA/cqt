package com.cqt.controller;

import com.cqt.models.Customer;
import com.cqt.models.Employee;
import com.cqt.models.Product;
import com.cqt.models.Query;
import com.cqt.service.CustomerService;
import com.cqt.service.EmployeeService;
import com.cqt.service.ProductService;
import com.cqt.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/queries")
public class QueryController {

    @Autowired
    private QueryService queryService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllQueries(Model model) {
        List<Query> query = queryService.getAllQueries();  // Fetch Query from service
        model.addAttribute("queries", query);  // Add queries to the model
        return "query/list";  // Return the Thymeleaf template
    }

    @GetMapping("/new")
    public String createQueryForm(Model model) {
        model.addAttribute("query", new Query()); // Create a new query if it's a create form, or load an existing query
        model.addAttribute("customers", customerService.getAllCustomers()); // Populate customers
        model.addAttribute("employees", employeeService.getAllEmployees()); // Populate employees
        model.addAttribute("products", productService.getAllProducts()); // Populate products
      return "query/query-form";
    }

    @PostMapping("/save")
    public String saveQuery(@ModelAttribute("query") Query query, BindingResult result, Model model) {
        // Validate that Customer, Employee, and Product are selected
        if (query.getCustomer() == null || query.getCustomer().getId() == null) {
            result.rejectValue("customer", "error.customer", "Customer must be selected");
        }
        if (query.getEmployee() == null || query.getEmployee().getId() == null) {
            result.rejectValue("employee", "error.employee", "Employee must be selected");
        }
        if (query.getProduct() == null || query.getProduct().getId() == null) {
            result.rejectValue("product", "error.product", "Product must be selected");
        }

        // Check for other validation errors
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("employees", employeeService.getAllEmployees());
            model.addAttribute("products", productService.getAllProducts());
            return "query-form"; // return back to form with error messages
        }

        // Save the query using QueryService
        queryService.saveQuery(query);

        // Redirect to the list of queries after successful save
        return "redirect:/queries";
    }

//    @PostMapping("/save")
//    public String saveQuery(@ModelAttribute("query") Query query) {
//        // Check if product name is present
//        if (query.getProduct().getName() == null || query.getProduct().getName().isEmpty()) {
//            throw new IllegalArgumentException("Product name must not be null or empty");
//        }
//
//        // Ensure the product, customer, and employee are not null
//        if (query.getProduct() == null || query.getCustomer() == null || query.getEmployee() == null) {
//            throw new IllegalArgumentException("Product, Customer, and Employee must not be null");
//        }
//
//        queryService.saveQuery(query);  // Save the query with the product
//        return "redirect:/queries";
//    }


    @GetMapping("/edit/{id}")
    public String editQuery(@PathVariable Long id, Model model) {
        Query query = queryService.getQueryById(id);

        // Initialize product if null
        if (query.getProduct() == null) {
            query.setProduct(new Product());
        }

        // Initialize customer if null
        if (query.getCustomer() == null) {
            query.setCustomer(new Customer());
        }

        // Initialize employee if null
        if (query.getEmployee() == null) {
            query.setEmployee(new Employee());
        }

        List<Product> products = productService.getAllProducts();  // Get all products
        List<Customer> customers = customerService.getAllCustomers();  // Get all customers
        List<Employee> employees = employeeService.getAllEmployees();  // Get all employees

        // Add the entities to the model
        model.addAttribute("query", query);
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);  // Add customers to the model
        model.addAttribute("employees", employees);  // Add employees to the model

        return "query/query-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuery(@PathVariable Long id) {
        queryService.deleteQuery(id);
        return "redirect:/queries";
    }
}

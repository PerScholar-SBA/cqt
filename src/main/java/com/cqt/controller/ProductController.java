package com.cqt.controller;

import com.cqt.models.Employee;
import com.cqt.models.Product;
import com.cqt.service.EmployeeService;
import com.cqt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> product = productService.getAllProducts();  // Fetch product from service
        model.addAttribute("products", product);  // Add products to the model
        model.addAttribute("page","products");
        return "products/list";  // Return the Thymeleaf template
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/product-form";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
//        List<Product> product = productService.getAllProducts();
        model.addAttribute("product", product);
//        model.addAttribute("products", products);  // Provide product for task assignment
        return "products/product-form";  // View for creating or editing a task
    }
    @PostMapping("/save")
    public String saveProuct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}

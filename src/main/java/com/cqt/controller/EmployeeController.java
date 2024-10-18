package com.cqt.controller;

import com.cqt.models.Employee;
import com.cqt.models.Role;
import com.cqt.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employee = employeeService.getAllEmployees();  // Fetch employee from service
        model.addAttribute("employees", employee);  // Add employees to the model
        model.addAttribute("page","employees");
        return "employees/list";  // Return the Thymeleaf template
    }


    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
//        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employee", employee);
//        model.addAttribute("employees", employees);  // Provide employees for task assignment
        return "employees/employee-form";  // View for creating or editing a task
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employee.setRole(Role.EMPLOYEE);
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
    @GetMapping("/new")
    public String showEmployeeForm(@RequestParam(required = false) Long id, Model model) {
        Employee employee = (id != null) ? employeeService.getEmployeeById(id) : new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }
}
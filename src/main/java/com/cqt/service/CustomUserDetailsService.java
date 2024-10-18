package com.cqt.service;

import com.cqt.models.Employee;
import com.cqt.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the employee from the repository
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Manually prefix the role with 'ROLE_' if it's not already prefixed
        String role = employee.getRole().name();
        String roleWithPrefix = "ROLE_" + role;
        System.out.println(roleWithPrefix);

        // Return the user details with prefixed role
        return new User(employee.getEmail(), employee.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleWithPrefix)));
    }
}

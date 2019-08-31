package com.tng.timesheetapp.service;

import java.security.Principal;
import java.util.List;

import com.tng.timesheetapp.model.employee.Employee;
import com.tng.timesheetapp.model.employee.EmployeeDetails;
import com.tng.timesheetapp.model.employee.UserRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tng.timesheetapp.model.employee.UserPrincipal;
import com.tng.timesheetapp.model.role.RoleRepository;

@CommonsLog
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = userRepository.findByUserName(username);
        if (user == null) {
            log.info("user nul exception!!");
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user);
    }

    public List<Employee> getAllEmployee() {
        return userRepository.findAll();
    }

    public boolean save(Employee employee) {

        if (userRepository.findByUserName(employee.getUserName()) == null) {
            employee.setStatus("NEW");
            employee.setPassword(new BCryptPasswordEncoder(10).encode(employee.getPassword()));
            userRepository.save(employee);
            return true;
        }

        return false;
    }

    public EmployeeDetails getEmployeeDetails() {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setRoles(roleRepository.findAll());
        return employeeDetails;
    }

    public Boolean setEmployeeDetails(EmployeeDetails employeeDetails) {

        if (userRepository.findByUserName(employeeDetails.getEmployee().getUserName()) == null) {
            employeeDetails.getEmployee().setStatus("NEW");
            employeeDetails.getEmployee()
                    .setPassword(new BCryptPasswordEncoder(10).encode(employeeDetails.getEmployee().getPassword()));
            // employeeDetails.getEmployee().setRoles(employeeDetails.getRoles());

            employeeDetails.setEmployee(userRepository.save(employeeDetails.getEmployee()));
        }

        employeeDetails.getRoles().stream().forEach(role -> {

            employeeRoleService.setEmployeeRoleByEmployeeAndRole(employeeDetails.getEmployee(),
                    roleRepository.findById(role.getId()).get());

        });

        return true;

    }

    public Employee findUserByPrincipal(Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        return ((UserPrincipal) userDetails).getUser();
    }

}

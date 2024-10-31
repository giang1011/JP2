package Controller;

import Entity.Employee;
import Service.EmployeeService;

import java.util.Map;
import java.util.Set;

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Set<Employee> searchByDepartmentName(String departmentName) {
        return employeeService.searchByDepartmentName(departmentName);
    }

    public Map<String, Set<Employee>> groupByDepartment() {
        return employeeService.groupByDepartment();
    }

    public Map<String, Long> countEmployeesByDepartment() {
        return employeeService.countEmployeesByDepartment();
    }

    public Map<String, Employee> searchByName(String keyword) {
        return employeeService.searchByName(keyword);
    }
}

package Service;

import Entity.Department;
import Entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private Map<Integer, Department> departmentMap;
    private Set<Employee> employees;

    public EmployeeService(Set<Employee> employees, List<Department> departments) {
        this.employees = employees;
        this.departmentMap = departments.stream().collect(Collectors.toMap(Department::getId, dept -> dept));
    }

    public Set<Employee> searchByDepartmentName(String departmentName) {
        return employees.stream()
                .filter(employee -> departmentMap.get(employee.getDeptId()).getName().equalsIgnoreCase(departmentName))
                .collect(Collectors.toSet());
    }

    public Map<String, Set<Employee>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> departmentMap.get(employee.getDeptId()).getName(),
                        Collectors.toSet()
                ));
    }

    public Map<String, Long> countEmployeesByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> departmentMap.get(employee.getDeptId()).getName(),
                        Collectors.counting()
                ));
    }

    public Map<String, Employee> searchByName(String keyword) {
        return employees.stream()
                .filter(employee -> employee.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toMap(Employee::getName, employee -> employee));
    }
}



import Controller.EmployeeController;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.EmployeeService;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
                new Department(1, "HR"),
                new Department(2, "IT"),
                new Department(3, "Sales")
        );

        Set<Employee> employees = new HashSet<>(Arrays.asList(
                new Employee(1, "John Doe", 1, LocalDate.of(1999,12,20), Gender.M, "New York", "NY", "1234567890"),
                new Employee(2, "Jane Smith", 2, LocalDate.of(2000,1,1), Gender.F, "San Francisco", "CA", "0987654321"),
                new Employee(3, "Jim Brown", 1, LocalDate.of(2001,10,12), Gender.F, "Austin", "TX", "1122334455")
        ));

        EmployeeService employeeService = new EmployeeService(employees, departments);
        EmployeeController employeeController = new EmployeeController(employeeService);

        System.out.println("Search by Department Name:");
        System.out.println(employeeController.searchByDepartmentName("HR"));

        System.out.println("\nGroup By Department:");
        System.out.println(employeeController.groupByDepartment());

        System.out.println("\nCount Employees by Department:");
        System.out.println(employeeController.countEmployeesByDepartment());

        System.out.println("\nSearch by Employee Name:");
        System.out.println(employeeController.searchByName("Jane"));
    }
}

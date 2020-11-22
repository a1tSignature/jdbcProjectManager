package di.com.a1tSign.projectManager.service.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;

import java.util.List;

public interface EmployeeService extends EmployeeHandler {

    List<Employee> findAll();
    void deleteByNameAndSurname(String name, String surname);
    void add(EmployeeImpl employee);
}

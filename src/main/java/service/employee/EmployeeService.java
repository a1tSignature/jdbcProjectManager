package service.employee;

import employee.Employee;
import employee.EmployeeImpl;

import java.util.List;

public interface EmployeeService extends EmployeeHandler {

    List<Employee> findAll();
    void deleteByNameAndSurname(String name, String surname);
    void add(EmployeeImpl employee);
}

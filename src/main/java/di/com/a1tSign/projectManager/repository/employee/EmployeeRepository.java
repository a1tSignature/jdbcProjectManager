package di.com.a1tSign.projectManager.repository.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();
    void deleteByNameAndSurname(String name, String surname);
    void add(EmployeeImpl employee);
}

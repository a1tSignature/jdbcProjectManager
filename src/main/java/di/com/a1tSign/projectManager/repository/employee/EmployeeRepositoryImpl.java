package di.com.a1tSign.projectManager.repository.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.repository.jdbc.employee.H2jdbcEmployeeEntityManagerImpl;
import di.com.a1tSign.projectManager.util.EmployeeParser;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Repository;
import di.org.springframework.beans.factory.stereotype.priority.Primary;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Repository
@Primary
public final class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private H2jdbcEmployeeEntityManagerImpl h2JdbcEmployeeEntityManagerImpl = new H2jdbcEmployeeEntityManagerImpl();

    public void setH2JdbcEmployeeEntityManagerImpl(H2jdbcEmployeeEntityManagerImpl h2JdbcEmployeeEntityManagerImpl) {
        this.h2JdbcEmployeeEntityManagerImpl = h2JdbcEmployeeEntityManagerImpl;
    }

    @Override
    public List<Employee> findAll() {
        List<List<String>> data = h2JdbcEmployeeEntityManagerImpl.getEmployees();
        return data.stream().map(EmployeeParser::parse).collect(Collectors.toList());
    }

    @Override
    public void deleteByNameAndSurname(String name, String surname) {
        h2JdbcEmployeeEntityManagerImpl.deleteByNameAndSurname(name, surname);
    }

    @Override
    public void add(EmployeeImpl employee) {
        h2JdbcEmployeeEntityManagerImpl.insertIntoEmployee(employee);
    }


}

package repository.employee;

import employee.Employee;
import employee.EmployeeImpl;
import lombok.AllArgsConstructor;
import repository.jdbc.employee.H2jdbcEmployeeEntityManager;
import util.EmployeeParser;

import java.util.List;
import java.util.stream.Collectors;

//@Repository
@AllArgsConstructor
public final class EmployeeRepositoryImpl implements EmployeeRepository {
    private final H2jdbcEmployeeEntityManager entityManager;

    @Override
    public List<Employee> findAll() {
        List<List<String>> data = entityManager.getEmployees();
        return data.stream().map(EmployeeParser::parse).collect(Collectors.toList());
    }

    @Override
    public void deleteByNameAndSurname(String name, String surname) {
        entityManager.deleteByNameAndSurname(name, surname);
    }

    @Override
    public void add(EmployeeImpl employee) {
        entityManager.insertIntoEmployee(employee);
    }


}

package di.com.a1tSign.projectManager.repository.jdbc.employee;

import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.employee.Rank;
import di.com.a1tSign.projectManager.employee.builder.EmployeeBuilder;
import di.com.a1tSign.projectManager.employee.builder.EmployeeBuilderImpl;
import di.com.a1tSign.projectManager.position.analyst.Analyst;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class H2jdbcEmployeeEntityManagerTest {

    private static H2jdbcEmployeeEntityManagerImpl h2jdbcEmployeeEntityManager;

    @BeforeAll
    static void instantiate() {
        h2jdbcEmployeeEntityManager = new H2jdbcEmployeeEntityManagerImpl();
    }

    @Test
    public void testGetProjects() {
        List<List<String>> data = h2jdbcEmployeeEntityManager.getEmployees();

        assertNotNull(data);
        assertTrue(data instanceof ArrayList);
    }

    @Test
    public void testSave() {
        int firstSize = h2jdbcEmployeeEntityManager.getEmployees().size();

        EmployeeBuilder builder = new EmployeeBuilderImpl();
        builder.setName("Alex");
        builder.setSurname("Vachovsky");
        builder.setPatronymic("Igorevich");
        builder.setPosition(new Analyst(PositionType.ANALYST, Specification.NONE));
        builder.setRank(Rank.JUNIOR);

        EmployeeImpl first = builder.getEmployee();

       h2jdbcEmployeeEntityManager.insertIntoEmployee(first);

        assertEquals(firstSize + 1, h2jdbcEmployeeEntityManager.getEmployees().size());
    }

    @Test
    public void testDelete() {
        int firstSize = h2jdbcEmployeeEntityManager.getEmployees().size();

        h2jdbcEmployeeEntityManager.deleteByNameAndSurname("Alex", "Vachovsky");

        assertEquals(firstSize - 1, h2jdbcEmployeeEntityManager.getEmployees().size());
    }
}

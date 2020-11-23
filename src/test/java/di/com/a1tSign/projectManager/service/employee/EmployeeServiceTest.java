package di.com.a1tSign.projectManager.service.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.employee.Rank;
import di.com.a1tSign.projectManager.position.developer.backend.IosBackendDeveloper;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private static EmployeeServiceImpl employeeService;

    @BeforeAll
    static void initialize() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void testRemainingTime() {
        List<Employee> list = new ArrayList<>();
        list.add(new EmployeeImpl("Vadim", "Boychenko", "Alekseevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.IOS), Rank.JUNIOR));

        Map<Employee, Integer> map =  employeeService.setStartRemainingTime(list, 6);

        assertEquals(map.get(list.get(0)), 48);

    }

}

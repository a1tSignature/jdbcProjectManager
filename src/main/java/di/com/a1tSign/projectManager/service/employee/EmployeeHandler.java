package di.com.a1tSign.projectManager.service.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.sprint.Sprint;

import java.util.List;
import java.util.Map;

public interface EmployeeHandler {
    Map<Employee, Integer> findByPositionType(PositionType positionType, Map<Employee, Integer> remainingTime);
    Map<Employee, Integer> setStartRemainingTime(List<Employee> employees, int daysBeforeDeadline);
    void distributeTime(String name, int time, Map<Employee, Integer> positionEmployees,
                        Sprint sprint, Map<Employee, Integer> remainingTime);
}

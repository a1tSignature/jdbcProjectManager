package service.employee;

import employee.Employee;
import position.developer.specification.PositionType;
import sprint.Sprint;

import java.util.List;
import java.util.Map;

public interface EmployeeHandler {
    Map<Employee, Integer> findByPositionType(PositionType positionType, Map<Employee, Integer> remainingTime);
    Map<Employee, Integer> setStartRemainingTime(List<Employee> employees, int daysBeforeDeadline);
    void distributeTime(String name, int time, Map<Employee, Integer> positionEmployees,
                        Sprint sprint, Map<Employee, Integer> remainingTime);
}

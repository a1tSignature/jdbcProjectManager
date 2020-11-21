package service.employee;

import employee.Employee;
import employee.EmployeeImpl;
import lombok.AllArgsConstructor;
import position.BasePosition;
import position.analyst.Analyst;
import position.developer.backend.AndroidBackendDeveloper;
import position.developer.backend.IosBackendDeveloper;
import position.developer.backend.JavaServerDeveloper;
import position.developer.frontend.AndroidFrontendDeveloper;
import position.developer.frontend.IosFrontendDeveloper;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;
import position.tester.Tester;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryImpl;
import sprint.Sprint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public final class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteByNameAndSurname(String name, String surname) {
        repository.deleteByNameAndSurname(name, surname);
    }

    @Override
    public void add(EmployeeImpl employee) {
        repository.add(employee);
    }

    @Override
    public Map<Employee, Integer> findByPositionType(PositionType positionType, Map<Employee, Integer> remainingTime) {
        Map<Employee, Integer> answer = new HashMap<>();

        for (Map.Entry<Employee, Integer> elem : remainingTime.entrySet()) {

            EmployeeImpl employee = (EmployeeImpl) elem.getKey();

            if (employee.getPosition().getPositionType() == positionType) {
                answer.put(employee, elem.getValue());
            }
        }

        return answer;
    }

    public Map<Employee, Integer> setStartRemainingTime(List<Employee> employees, int daysBeforeDeadline) {
        Map<Employee, Integer> remainingTime = new HashMap<>();

        if (employees != null) {
            for (Employee employee : employees) {
                remainingTime.put(employee,
                        remainingTime.get(employee) != null ? remainingTime.get(employee)
                                + daysBeforeDeadline * 8 : daysBeforeDeadline * 8);
            }
        }

        return remainingTime;
    }

    public void distributeTime(String name, int time, Map<Employee, Integer> positionEmployees,
                               Sprint sprint, Map<Employee, Integer> remainingTime) {

        for (Map.Entry<Employee, Integer> elem : positionEmployees.entrySet()) {

            EmployeeImpl employee = (EmployeeImpl) elem.getKey();

            if (name.equals(employee.getName()) && elem.getValue() >= time) {

                int firstTime = positionEmployees.get(employee);

                System.out.println(employee.getFullName() + " started his work");
                employee.getPosition().doWork(actionCodeParser(sprint.getName(), sprint.getSprintType()));

                positionEmployees.put(employee, firstTime - time);
                remainingTime.put(employee, firstTime - time);
                sprint.setElapsedTime(time);
            }
        }
    }

    private int actionCodeParser(String name, PositionType positionType) {

        int actionCode = -1;

        if (positionType == PositionType.BACKEND)
            switch (name) {
                case "Database":
                    actionCode = 0;
                    break;
                case "Security":
                    actionCode = 1;
                    break;
                case "Service":
                    actionCode = 2;
            }
        else if (positionType == PositionType.FRONTEND) {
            switch (name) {
                case "Forms":
                    actionCode = 0;
                    break;
                case "Displaying":
                    actionCode = 1;
                    break;
                case "Metrica":
                    actionCode = 2;
            }
        }

        return actionCode;
    }

}

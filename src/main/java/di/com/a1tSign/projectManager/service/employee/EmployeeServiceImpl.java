package di.com.a1tSign.projectManager.service.employee;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.repository.employee.EmployeeRepository;
import di.com.a1tSign.projectManager.repository.employee.EmployeeRepositoryImpl;
import di.com.a1tSign.projectManager.sprint.Sprint;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public final class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl() {
    }

    @Autowired
    private EmployeeRepository employeeRepositoryImpl;

    public void setEmployeeRepositoryImpl(EmployeeRepositoryImpl employeeRepositoryImpl) {
        this.employeeRepositoryImpl = employeeRepositoryImpl;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepositoryImpl.findAll();
    }

    @Override
    public void deleteByNameAndSurname(String name, String surname) {
        employeeRepositoryImpl.deleteByNameAndSurname(name, surname);
    }

    @Override
    public void add(EmployeeImpl employee) {
        employeeRepositoryImpl.add(employee);
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

package manager;

import employee.Employee;
import employee.EmployeeImpl;
import position.developer.specification.PositionType;
import project.Project;
import repository.employee.EmployeeRepositoryImpl;
import repository.project.ProjectRepositoryImpl;
import repository.jdbc.employee.H2jdbcEmployeeEntityManager;
import repository.jdbc.project.H2jdbcProjectEntityManager;
import service.employee.EmployeeService;
import service.employee.EmployeeServiceImpl;
import service.project.ProjectService;
import service.project.ProjectServiceImpl;
import sprint.Sprint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConsoleManager {

    private final H2jdbcEmployeeEntityManager employeeEntityManager = new H2jdbcEmployeeEntityManager();
    private final H2jdbcProjectEntityManager projectEntityManager = new H2jdbcProjectEntityManager();

    private final EmployeeRepositoryImpl employeeRepositoryImpl = new EmployeeRepositoryImpl(employeeEntityManager);
    private final ProjectRepositoryImpl projectRepositoryImpl = new ProjectRepositoryImpl(projectEntityManager);

    private final ProjectService projectService = new ProjectServiceImpl(projectRepositoryImpl);
    private final EmployeeService employeeService = new EmployeeServiceImpl(employeeRepositoryImpl);

    private final List<Employee> employees = employeeRepositoryImpl.findAll();
    private final List<Project> projects = projectRepositoryImpl.findAll();

    private Map<Employee, Integer> remainingTime;

    public ProjectService getProjectService() {
        return projectService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public ConsoleManager(int daysBeforeDeadline) {
        remainingTime = employeeService.setStartRemainingTime(employees, daysBeforeDeadline);
    }

    public Map<Employee, Integer> getRemainingTime() {
        return remainingTime;
    }

    public Project work(int numberOfProject) {


        if (numberOfProject < 0 || numberOfProject > projects.size()) {
            System.out.println("Incorrect number of project");
            return null;
        }

        Project project = projects.get(numberOfProject);

        System.out.println("Project management " + project.getTitle() + " started");
        System.out.println("Sprints: ");
        showSprints(project);

        return project;
    }

    public Map<Employee, Integer> management(Sprint sprint) {

        System.out.println("####################################");
        System.out.println(sprint.getName());

        Map<Employee, Integer> spr1 = employeeService.findByPositionType(sprint.getSprintType(), remainingTime);
        showEmployees(spr1);

        return spr1;
    }


    private void showEmployees(Map<Employee, Integer> positionEmployees) {

        for (Map.Entry<Employee, Integer> elem : positionEmployees.entrySet()) {
            EmployeeImpl employee = (EmployeeImpl) elem.getKey();
            System.out.println("---------------------------------------------------------");
            System.out.println(employee.info() + " remaining hours: " + elem.getValue());
            System.out.println("---------------------------------------------------------");
            System.out.println("**********************************************************");
        }
    }

    public void showProjects() {
        for (Project project : projects) {
            System.out.println(project.getTitle());
            System.out.println("**********************************************************");
        }
    }

    private void showSprints(Project project) {

        for (Sprint sprint : project.getSprints()) {
            System.out.println(sprint.getName());
            System.out.println("*****************");
        }
    }

}

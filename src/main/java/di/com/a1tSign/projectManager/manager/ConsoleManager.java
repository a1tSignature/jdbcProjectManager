package di.com.a1tSign.projectManager.manager;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.service.employee.EmployeeService;
import di.com.a1tSign.projectManager.service.employee.EmployeeServiceImpl;
import di.com.a1tSign.projectManager.service.project.ProjectService;
import di.com.a1tSign.projectManager.service.project.ProjectServiceImpl;
import di.com.a1tSign.projectManager.sprint.Sprint;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;

import java.util.List;
import java.util.Map;

//TODO: convert to a REST service?
@SuppressWarnings("unused")
@Component
@Primary
public class ConsoleManager {

    public ConsoleManager() {
    }

    @Autowired
    private ProjectService projectServiceImpl;
    @Autowired
    private EmployeeService employeeServiceImpl;

    private  List<Project> projects;
    private Map<Employee, Integer> remainingTime;

    private int daysBeforeDeadline;

    public void setDaysBeforeDeadline(int daysBeforeDeadline) {
        this.daysBeforeDeadline = daysBeforeDeadline;
    }

    public void setProjectServiceImpl(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    public void setEmployeeServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    public ProjectService getProjectServiceImpl() {
        return projectServiceImpl;
    }

    public EmployeeService getEmployeeServiceImpl() {
        return employeeServiceImpl;
    }

    //@PostConstruct
    public void setRemainingTime() {
        this.remainingTime = employeeServiceImpl.setStartRemainingTime(employeeServiceImpl.findAll(), daysBeforeDeadline);
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

        //remainingTime = employeeServiceImpl.setStartRemainingTime(employeeServiceImpl.findAll(), daysBeforeDeadline);
        return project;
    }

    public Map<Employee, Integer> management(Sprint sprint) {

        System.out.println("####################################");
        System.out.println(sprint.getName());

        Map<Employee, Integer> spr1 = employeeServiceImpl.findByPositionType(sprint.getSprintType(), remainingTime);
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

        projects = projectServiceImpl.findAll();
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

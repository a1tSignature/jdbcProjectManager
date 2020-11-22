package di.com.a1tSign.projectManager;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.manager.ConsoleManager;
import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.service.employee.EmployeeService;
import di.com.a1tSign.projectManager.service.project.ProjectService;
import di.com.a1tSign.projectManager.sprint.Sprint;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;
import di.org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

@Component
@Primary
public class Main {

    @Autowired
    static private ConsoleManager consoleManager;

    public static void setConsoleManager(ConsoleManager consoleManager) {
        Main.consoleManager = consoleManager;
    }

    public static void main(String[] args) throws SQLException, ReflectiveOperationException {

        ApplicationContext applicationContext = new ApplicationContext("");
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert the work period (days): ");
        int deadline = sc.nextInt();

        start(sc, deadline);

        System.out.println("Want to manage another di.com.a1tSign.projectManager.project?");
        int code = sc.nextInt();

        if(code == 0) {
            start(sc, deadline);
        } else {
            sc.close();
            System.out.println("End of the management");

            applicationContext.close();
        }
    }

    private static void start(Scanner sc, int deadline) {

        consoleManager.setDaysBeforeDeadline(deadline);
        final EmployeeService employeeService = consoleManager.getEmployeeServiceImpl();
        final ProjectService projectService = consoleManager.getProjectServiceImpl();

        consoleManager.showProjects();

        System.out.println("Choose di.com.a1tSign.projectManager.project");

        int numOfProject = sc.nextInt();

        Project project = consoleManager.work(numOfProject);

        if(project != null) {

            for (Sprint sprint : project.getSprints()) {

                Map<Employee, Integer> spr = consoleManager.management(sprint);

                int t = sc.nextInt();
                String s = sc.next();

                employeeService.distributeTime(s, t, spr, sprint, consoleManager.getRemainingTime());
            }

            System.out.println("Time allocated for the di.com.a1tSign.projectManager.project: " + projectService.getFullTime(project));
        }

        //sc.close();
    }
}

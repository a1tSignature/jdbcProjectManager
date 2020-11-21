import employee.Employee;
import manager.ConsoleManager;
import project.Project;
import service.employee.EmployeeService;
import service.project.ProjectService;
import sprint.Sprint;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Insert the work period (days): ");
        int deadline = sc.nextInt();

        start(sc, deadline);

        System.out.println("Want to manage another project?");
        int code = sc.nextInt();

        if(code == 0) {
            start(sc, deadline);
        } else {
            sc.close();
            System.out.println("End of the management");
        }
    }

    private static void start(Scanner sc, int deadline) {

        final ConsoleManager consoleManager = new ConsoleManager(deadline);
        final EmployeeService employeeService = consoleManager.getEmployeeService();
        final ProjectService projectService = consoleManager.getProjectService();

        consoleManager.showProjects();

        System.out.println("Choose project");

        int numOfProject = sc.nextInt();

        Project project = consoleManager.work(numOfProject);

        if(project != null) {

            for (Sprint sprint : project.getSprints()) {

                Map<Employee, Integer> spr = consoleManager.management(sprint);

                int t = sc.nextInt();
                String s = sc.next();

                employeeService.distributeTime(s, t, spr, sprint, consoleManager.getRemainingTime());
            }

            System.out.println("Time allocated for the project: " + projectService.getFullTime(project));
        }

        //sc.close();
    }
}

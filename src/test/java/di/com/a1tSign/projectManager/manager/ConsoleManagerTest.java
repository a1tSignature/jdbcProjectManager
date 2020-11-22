//package di.com.a1tSign.projectManager.manager;
//
//import di.com.a1tSign.projectManager.employee.Employee;
//import di.com.a1tSign.projectManager.employee.EmployeeImpl;
//import org.junit.Assert;
//import org.junit.Test;
//import di.com.a1tSign.projectManager.position.developer.backend.BackendDeveloper;
//import di.com.a1tSign.projectManager.project.Project;
//import di.com.a1tSign.projectManager.repository.di.com.a1tSign.projectManager.project.ProjectRepository;
//import di.com.a1tSign.projectManager.service.di.com.a1tSign.projectManager.project.ProjectService;
//import di.com.a1tSign.projectManager.service.di.com.a1tSign.projectManager.project.ProjectServiceImpl;
//import di.com.a1tSign.projectManager.sprint.Sprint;
//
//import java.di.com.a1tSign.projectManager.util.List;
//import java.di.com.a1tSign.projectManager.util.Map;
//
//public class ConsoleManagerTest {
//
//    private final ProjectService projectService = new ProjectServiceImpl();
//    private final ProjectRepository projectRepository = new ProjectRepository(projectService);
//
//    private final List<Project> projects = projectRepository.getProjects();
//
//
//    private final ConsoleManager di.com.a1tSign.projectManager.manager = new ConsoleManager(7);
//    private final Project actual = projects.get(0);
//    private final Project excepted = di.com.a1tSign.projectManager.manager.work(0);
//
//    @Test
//    public void work() {
//
//        Assert.assertEquals(actual.getTitle(), excepted.getTitle());
//        Assert.assertEquals(actual.getSprints().size(), excepted.getSprints().size());
//        Assert.assertEquals(actual.getProjectType(), excepted.getProjectType());
//    }
//
//    @Test
//    public void management() {
//
//        List<Sprint> exceptedSprints = excepted.getSprints();
//        Map<Employee, Integer> excepted = di.com.a1tSign.projectManager.manager.management(exceptedSprints.get(0));
//
//        for (Map.Entry<Employee, Integer> elem : excepted.entrySet()) {
//            EmployeeImpl di.com.a1tSign.projectManager.employee = (EmployeeImpl) elem.getKey();
//
//            Assert.assertTrue(di.com.a1tSign.projectManager.employee.getPosition() instanceof BackendDeveloper);
//        }
//    }
//
//    @Test
//    public void distributeTime() {
//
//        Map<Employee, Integer> map = di.com.a1tSign.projectManager.manager.management(excepted.getSprints().get(0));
//
//        di.com.a1tSign.projectManager.manager.distributeTime("Vadim", 20,
//                map, excepted.getSprints().get(0));
//        for(Map.Entry<Employee, Integer> elem : map.entrySet()) {
//            EmployeeImpl di.com.a1tSign.projectManager.employee = (EmployeeImpl) elem.getKey();
//
//            if(di.com.a1tSign.projectManager.employee.getName().equals("Vadim"))
//                Assert.assertEquals(elem.getValue().intValue(), 36);
//        }
//    }
//}

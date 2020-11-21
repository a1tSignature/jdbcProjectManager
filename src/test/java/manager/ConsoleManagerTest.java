//package manager;
//
//import employee.Employee;
//import employee.EmployeeImpl;
//import org.junit.Assert;
//import org.junit.Test;
//import position.developer.backend.BackendDeveloper;
//import project.Project;
//import repository.project.ProjectRepository;
//import service.project.ProjectService;
//import service.project.ProjectServiceImpl;
//import sprint.Sprint;
//
//import java.util.List;
//import java.util.Map;
//
//public class ConsoleManagerTest {
//
//    private final ProjectService projectService = new ProjectServiceImpl();
//    private final ProjectRepository projectRepository = new ProjectRepository(projectService);
//
//    private final List<Project> projects = projectRepository.getProjects();
//
//
//    private final ConsoleManager manager = new ConsoleManager(7);
//    private final Project actual = projects.get(0);
//    private final Project excepted = manager.work(0);
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
//        Map<Employee, Integer> excepted = manager.management(exceptedSprints.get(0));
//
//        for (Map.Entry<Employee, Integer> elem : excepted.entrySet()) {
//            EmployeeImpl employee = (EmployeeImpl) elem.getKey();
//
//            Assert.assertTrue(employee.getPosition() instanceof BackendDeveloper);
//        }
//    }
//
//    @Test
//    public void distributeTime() {
//
//        Map<Employee, Integer> map = manager.management(excepted.getSprints().get(0));
//
//        manager.distributeTime("Vadim", 20,
//                map, excepted.getSprints().get(0));
//        for(Map.Entry<Employee, Integer> elem : map.entrySet()) {
//            EmployeeImpl employee = (EmployeeImpl) elem.getKey();
//
//            if(employee.getName().equals("Vadim"))
//                Assert.assertEquals(elem.getValue().intValue(), 36);
//        }
//    }
//}

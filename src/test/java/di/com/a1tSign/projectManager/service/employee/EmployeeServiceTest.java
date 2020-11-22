//package di.com.a1tSign.projectManager.service.di.com.a1tSign.projectManager.employee.factory;
//
//import di.com.a1tSign.projectManager.employee.Employee;
//import di.com.a1tSign.projectManager.employee.EmployeeImpl;
//import di.com.a1tSign.projectManager.employee.Rank;
//import org.junit.Assert;
//import org.junit.Test;
//import di.com.a1tSign.projectManager.position.developer.backend.AndroidBackendDeveloper;
//import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
//import di.com.a1tSign.projectManager.position.developer.specification.Specification;
//
//public class EmployeeServiceTest {
//
//    @Test
//    public void createEmployee() {
//
//        String name = "Alex";
//        String surname = "Korotkov";
//        String patronymic = "Tarasovich";
//        AndroidBackendDeveloper di.com.a1tSign.projectManager.position = new AndroidBackendDeveloper(PositionType.BACKEND, Specification.ANDROID);
//        Rank rank = Rank.JUNIOR;
//
//        EmployeeImpl actual = new EmployeeImpl(name, surname, patronymic, di.com.a1tSign.projectManager.position, rank);
//
//        EmployeeService employeeService = new EmployeeService();
//        Employee excepted = employeeService.createEmployee(
//                "Alex",
//                "Korotkov",
//                "Tarasovich",
//                PositionType.BACKEND,
//                Specification.ANDROID,
//                Rank.JUNIOR
//
//        );
//
//        Assert.assertEquals(((EmployeeImpl) excepted).getPosition().getClass(), actual.getPosition().getClass());
//    }
//}

//package service.employee.factory;
//
//import employee.Employee;
//import employee.EmployeeImpl;
//import employee.Rank;
//import org.junit.Assert;
//import org.junit.Test;
//import position.developer.backend.AndroidBackendDeveloper;
//import position.developer.specification.PositionType;
//import position.developer.specification.Specification;
//
//public class EmployeeServiceTest {
//
//    @Test
//    public void createEmployee() {
//
//        String name = "Alex";
//        String surname = "Korotkov";
//        String patronymic = "Tarasovich";
//        AndroidBackendDeveloper position = new AndroidBackendDeveloper(PositionType.BACKEND, Specification.ANDROID);
//        Rank rank = Rank.JUNIOR;
//
//        EmployeeImpl actual = new EmployeeImpl(name, surname, patronymic, position, rank);
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

package di.com.a1tSign.projectManager.repository.jdbc;

import di.com.a1tSign.projectManager.employee.Employee;
import di.com.a1tSign.projectManager.employee.EmployeeImpl;
import di.com.a1tSign.projectManager.employee.Rank;
import di.com.a1tSign.projectManager.position.developer.backend.IosBackendDeveloper;
import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.position.developer.specification.Specification;
import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;
import di.com.a1tSign.projectManager.repository.jdbc.employee.H2jdbcEmployeeEntityManagerImpl;
import di.com.a1tSign.projectManager.repository.jdbc.project.H2jdbcProjectEntityManagerImpl;
import di.com.a1tSign.projectManager.service.employee.EmployeeService;
import di.com.a1tSign.projectManager.service.employee.EmployeeServiceImpl;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;
import di.org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class Test {

    @Autowired
    static private EmployeeService employeeServiceImpl;

    public static void setEmployeeServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        Test.employeeServiceImpl = employeeServiceImpl;
    }

    public static void main(String[] args) throws SQLException, ReflectiveOperationException {

        H2jdbcEmployeeEntityManagerImpl create = new H2jdbcEmployeeEntityManagerImpl();
        H2jdbcProjectEntityManagerImpl projectEntityManager = new H2jdbcProjectEntityManagerImpl();

        create.deleteAll();

        create.create();

        create.insertInitial();
        projectEntityManager.insertIntoProject(new Project("title", new ArrayList<>(), ProjectType.BACKEND_ONLY));

        create.insertIntoEmployee(new EmployeeImpl("Vadim", "Boychenko", "Alekseevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.IOS), Rank.JUNIOR));
        create.insertIntoEmployee(new EmployeeImpl("Ivan", "Smetanin", "Yurievich",
                new IosBackendDeveloper(PositionType.FRONTEND, Specification.ANDROID), Rank.MIDDLE));
        create.insertIntoEmployee(new EmployeeImpl("Danil", "Davydenko", "Sergeevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.JAVASERVER), Rank.SENIOR));
        create.insertIntoEmployee(new EmployeeImpl("Dmitry", "Glagolev", "Olegovich",
                new IosBackendDeveloper(PositionType.FRONTEND, Specification.IOS), Rank.TEAMLEAD));
        create.insertIntoEmployee(new EmployeeImpl("Alexander", "Ryadnov", "Sergeevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.ANDROID), Rank.SENIOR));
        create.insertIntoEmployee(new EmployeeImpl("Dmitry", "Arapov", "Sergeevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.JAVASERVER), Rank.JUNIOR));

//        create.getEmployees();
//
//        //create.deleteByNameAndSurname("Vadim", "Boychenko");
//
//        System.out.println("After delete");
//        create.getEmployees();

        ApplicationContext applicationContext = new ApplicationContext("");

        List<Employee> list = employeeServiceImpl.findAll();
        System.out.println("ok");

        applicationContext.close();

    }

}

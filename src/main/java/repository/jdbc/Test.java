package repository.jdbc;

import employee.EmployeeImpl;
import employee.Rank;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import position.developer.backend.IosBackendDeveloper;
import position.developer.specification.PositionType;
import position.developer.specification.Specification;
import project.Project;
import project.ProjectType;
import repository.jdbc.employee.H2jdbcEmployeeEntityManager;
import repository.jdbc.project.H2jdbcProjectEntityManager;
import sprint.Sprint;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

public class Test {

    public static void main(String[] args) throws SQLException {
        H2jdbcEmployeeEntityManager create = new H2jdbcEmployeeEntityManager();
        H2jdbcProjectEntityManager projectEntityManager = new H2jdbcProjectEntityManager();

        create.deleteAll();

        create.create();

        create.insertInitial();
        projectEntityManager.insertIntoProject(new Project("title", new ArrayList<Sprint>(), ProjectType.BACKEND_ONLY));

        create.insertIntoEmployee(new EmployeeImpl("Vadim", "Boychenko", "Alekseevich",
                new IosBackendDeveloper(PositionType.BACKEND, Specification.IOS), Rank.JUNIOR));

        create.getEmployees();

        create.deleteByNameAndSurname("Vadim", "Boychenko");

        System.out.println("After delete");
        create.getEmployees();
    }

}

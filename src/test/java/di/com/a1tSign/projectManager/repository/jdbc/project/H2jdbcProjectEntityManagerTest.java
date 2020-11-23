package di.com.a1tSign.projectManager.repository.jdbc.project;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;
import di.com.a1tSign.projectManager.project.builder.ProjectBuilderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class H2jdbcProjectEntityManagerTest {

    private static H2jdbcProjectEntityManagerImpl h2jdbcProjectEntityManager;

    @BeforeAll
    static void initialize() {
        h2jdbcProjectEntityManager = new H2jdbcProjectEntityManagerImpl();
    }

    @Test
    public void testGetProjects() {
        List<List<String>> data = h2jdbcProjectEntityManager.getProjects();

        assertNotNull(data);
        assertTrue(data instanceof ArrayList);
    }

    @Test
    public void testSave() {

        //h2jdbcProjectEntityManager.deleteByTitle("title1");

        int firstSize = h2jdbcProjectEntityManager.getProjects().size();

        ProjectBuilderImpl builder = new ProjectBuilderImpl();
        builder.setTitle("title1");
        builder.setProjectType(ProjectType.BACKEND_ONLY);
        builder.setSprints(ProjectType.BACKEND_ONLY);

        Project first = builder.getProject();

        h2jdbcProjectEntityManager.insertIntoProject(first);

        assertEquals(firstSize + 1, h2jdbcProjectEntityManager.getProjects().size());
    }

    @Test
    public void testDelete() {
        int firstSize = h2jdbcProjectEntityManager.getProjects().size();

        h2jdbcProjectEntityManager.deleteByTitle("title1");

        assertEquals(firstSize - 1, h2jdbcProjectEntityManager.getProjects().size());
    }


}

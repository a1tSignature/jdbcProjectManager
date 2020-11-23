package di.com.a1tSign.projectManager.repository.jdbc.project;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;
import di.com.a1tSign.projectManager.project.builder.ProjectBuilder;
import di.com.a1tSign.projectManager.project.builder.ProjectBuilderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class H2jdbcProjectEntityManagerTest {

    private static H2jdbcProjectEntityManagerImpl h2jdbcProjectEntityManager;
    private static Project testProject;

    @BeforeAll
    static void initialize() {
        h2jdbcProjectEntityManager = new H2jdbcProjectEntityManagerImpl();

        ProjectBuilder builder = new ProjectBuilderImpl();
        builder.setTitle("test");
        builder.setProjectType(ProjectType.FRONTEND_ONLY);
        builder.setSprints(ProjectType.FRONTEND_ONLY);

        testProject = builder.getProject();
    }

    @Test
    public void testGetProjects() {
        List<List<String>> data = h2jdbcProjectEntityManager.getProjects();

        assertNotNull(data);
        assertTrue(data.stream().anyMatch((e) -> e.stream().anyMatch(Objects::nonNull)));
    }

    @Test
    public void testSave() {

        int firstSize = h2jdbcProjectEntityManager.getProjects().size();

        h2jdbcProjectEntityManager.insertIntoProject(testProject);

        assertEquals(firstSize + 1, h2jdbcProjectEntityManager.getProjects().size());

        assertEquals(testProject.getTitle(), h2jdbcProjectEntityManager.findByTitle(testProject.getTitle()).get(0));

        h2jdbcProjectEntityManager.deleteByTitle(testProject.getTitle());
    }

    @Test
    public void testDelete() {
        int firstSize = h2jdbcProjectEntityManager.getProjects().size();

        h2jdbcProjectEntityManager.insertIntoProject(testProject);
        assertEquals(firstSize + 1, h2jdbcProjectEntityManager.getProjects().size());

        h2jdbcProjectEntityManager.deleteByTitle(testProject.getTitle());
        assertEquals(firstSize, h2jdbcProjectEntityManager.getProjects().size());
    }

    @Test
    public void testFindByTitle() {
        h2jdbcProjectEntityManager.insertIntoProject(testProject);
        assertEquals(testProject.getTitle(), h2jdbcProjectEntityManager.findByTitle(testProject.getTitle()).get(0));
        h2jdbcProjectEntityManager.deleteByTitle(testProject.getTitle());
    }

    @Test
    public void testUpdateProject() {
        h2jdbcProjectEntityManager.insertIntoProject(testProject);

        ProjectBuilder builder = new ProjectBuilderImpl();
        builder.setTitle("test1");
        builder.setProjectType(ProjectType.FRONTEND_ONLY);
        builder.setSprints(ProjectType.FRONTEND_ONLY);

        Project testProject1 = builder.getProject();

        h2jdbcProjectEntityManager.updateProject(testProject.getTitle(), testProject1);
        assertNotNull(h2jdbcProjectEntityManager.findByTitle(testProject1.getTitle()));

        h2jdbcProjectEntityManager.deleteByTitle("test1");
    }

}

package di.com.a1tSign.projectManager.repository.project;

import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Component;
import di.org.springframework.beans.factory.stereotype.priority.Primary;
import lombok.AllArgsConstructor;
import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.repository.jdbc.project.H2jdbcProjectEntityManagerImpl;
import di.com.a1tSign.projectManager.util.ProjectParser;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Component
@Primary
public final class ProjectRepositoryImpl implements ProjectRepository {

    @Autowired
    private H2jdbcProjectEntityManagerImpl h2JdbcProjectEntityManagerImpl;

    public void setH2JdbcProjectEntityManagerImpl(H2jdbcProjectEntityManagerImpl h2JdbcProjectEntityManagerImpl) {
        this.h2JdbcProjectEntityManagerImpl = h2JdbcProjectEntityManagerImpl;

        System.out.println(h2JdbcProjectEntityManagerImpl.getProjects());
    }

    public List<Project> findAll() {
        List<List<String>> data = h2JdbcProjectEntityManagerImpl.getProjects();
        return data.stream().map(ProjectParser::parse).collect(Collectors.toList());
    }

    public void deleteByTitle(String title) {
        h2JdbcProjectEntityManagerImpl.deleteByTitle(title);
    }

    public void add(Project project) {
        h2JdbcProjectEntityManagerImpl.insertIntoProject(project);
    }
}

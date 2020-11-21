package repository.project;

import lombok.AllArgsConstructor;
import project.Project;
import project.ProjectType;
import repository.jdbc.project.H2jdbcProjectEntityManager;
import service.project.ProjectService;
import util.ProjectParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public final class ProjectRepositoryImpl implements ProjectRepository {

    private final H2jdbcProjectEntityManager entityManager;

    public List<Project> findAll() {
        List<List<String>> data = entityManager.getProjects();
        return data.stream().map(ProjectParser::parse).collect(Collectors.toList());
    }

    public void deleteByTitle(String title) {
        entityManager.deleteByTitle(title);
    }

    public void add(Project project) {
        entityManager.insertIntoProject(project);
    }
}

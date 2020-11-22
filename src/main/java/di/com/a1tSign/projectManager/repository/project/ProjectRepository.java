package di.com.a1tSign.projectManager.repository.project;

import di.com.a1tSign.projectManager.project.Project;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAll();
    void deleteByTitle(String title);
    void add(Project project);
}

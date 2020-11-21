package repository.project;

import project.Project;

import java.util.List;

public interface ProjectRepository {

    List<Project> findAll();
    void deleteByTitle(String title);
    void add(Project project);
}

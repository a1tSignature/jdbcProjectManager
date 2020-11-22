package di.com.a1tSign.projectManager.service.project;

import di.com.a1tSign.projectManager.project.Project;

import java.util.List;

public interface ProjectService {

    double getFullTime(Project project);

    List<Project> findAll();
    void deleteByTitle(String title);
    void add(Project project);


}

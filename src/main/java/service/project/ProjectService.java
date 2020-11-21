package service.project;

import employee.Employee;
import position.developer.specification.PositionType;
import project.Project;
import project.ProjectType;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    double getFullTime(Project project);

    List<Project> findAll();
    void deleteByTitle(String title);
    void add(Project project);


}

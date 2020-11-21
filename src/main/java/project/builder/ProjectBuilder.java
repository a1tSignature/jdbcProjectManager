package project.builder;

import position.developer.specification.Specification;
import project.Project;
import project.ProjectType;

public interface ProjectBuilder {

    void setTitle(String title);

    void setSprints(ProjectType projectType);

    void setProjectType(ProjectType projectType);

    Project getProject();
}

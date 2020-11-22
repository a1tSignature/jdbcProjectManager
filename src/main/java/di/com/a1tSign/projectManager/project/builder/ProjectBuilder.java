package di.com.a1tSign.projectManager.project.builder;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;

public interface ProjectBuilder {

    void setTitle(String title);

    void setSprints(ProjectType projectType);

    void setProjectType(ProjectType projectType);

    Project getProject();
}

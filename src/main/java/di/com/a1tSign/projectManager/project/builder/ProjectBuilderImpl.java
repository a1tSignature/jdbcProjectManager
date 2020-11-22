package di.com.a1tSign.projectManager.project.builder;

import di.com.a1tSign.projectManager.position.developer.specification.PositionType;
import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;
import di.com.a1tSign.projectManager.sprint.Sprint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ProjectBuilderImpl implements ProjectBuilder {

    private String title;
    private List<Sprint> sprints;
    private ProjectType projectType;

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }


    @Override
    public void setSprints(ProjectType projectType) {

        if (projectType == ProjectType.FULLSTACK) {
            sprints = new ArrayList<>(
                    Arrays.asList(
                            new Sprint("Database", 0, PositionType.BACKEND),
                            new Sprint("Security", 0, PositionType.BACKEND),
                            new Sprint("Service", 0, PositionType.BACKEND),
                            new Sprint("Forms", 0, PositionType.FRONTEND),
                            new Sprint("Displaying", 0, PositionType.FRONTEND),
                            new Sprint("Metrica", 0, PositionType.FRONTEND)
                    )
            );
        } else if (projectType == ProjectType.BACKEND_ONLY) {
            sprints = new ArrayList<>(
                    Arrays.asList(
                            new Sprint("Database", 0, PositionType.BACKEND),
                            new Sprint("Security", 0, PositionType.BACKEND),
                            new Sprint("Service", 0, PositionType.BACKEND)
                    )
            );
        } else if (projectType == ProjectType.FRONTEND_ONLY) {
            sprints = new ArrayList<>(
                    Arrays.asList(
                            new Sprint("Forms", 0, PositionType.FRONTEND),
                            new Sprint("Displaying", 0, PositionType.FRONTEND),
                            new Sprint("Metrica", 0, PositionType.FRONTEND)
                    )
            );
        }
    }

    @Override
    public Project getProject() {
        return new Project(title, sprints, projectType);
    }
}

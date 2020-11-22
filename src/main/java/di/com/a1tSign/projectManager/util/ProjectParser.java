package di.com.a1tSign.projectManager.util;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.project.ProjectType;
import di.com.a1tSign.projectManager.project.builder.ProjectBuilder;
import di.com.a1tSign.projectManager.project.builder.ProjectBuilderImpl;

import java.util.List;

public final class ProjectParser {

    private static final ProjectBuilder builder = new ProjectBuilderImpl();

    public static Project parse(List<String> data) {
        builder.setTitle(data.get(0));
        builder.setProjectType(parseType(data.get(1)));
        builder.setSprints(parseType(data.get(1)));

        return builder.getProject();
    }

    private static ProjectType parseType(String position) {
        ProjectType positionType = null;
        var list = ProjectType.values();
        for(ProjectType pos : list) {
            if(pos.name().equalsIgnoreCase(position)) positionType = pos;
        }

        return positionType;
    }
}

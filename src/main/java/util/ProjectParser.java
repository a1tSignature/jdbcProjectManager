package util;

import project.Project;
import project.ProjectType;
import project.builder.ProjectBuilder;
import project.builder.ProjectBuilderImpl;

import java.util.List;

public final class ProjectParser {

    public static Project parse(List<String> data) {
        ProjectBuilder builder = new ProjectBuilderImpl();

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

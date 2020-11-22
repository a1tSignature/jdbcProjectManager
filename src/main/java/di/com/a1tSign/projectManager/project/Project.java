package di.com.a1tSign.projectManager.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import di.com.a1tSign.projectManager.sprint.Sprint;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Project {

    private String title;
    private List<Sprint> sprints;
    private ProjectType projectType;

}

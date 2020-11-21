package project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import position.developer.specification.Specification;
import sprint.Sprint;

import java.util.List;

@Getter
@AllArgsConstructor
public final class Project {

    private String title;
    private List<Sprint> sprints;
    private ProjectType projectType;

}

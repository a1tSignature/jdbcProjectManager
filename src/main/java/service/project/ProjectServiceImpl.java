package service.project;

import lombok.AllArgsConstructor;
import project.Project;
import repository.project.ProjectRepository;
import sprint.Sprint;

import java.util.List;

@AllArgsConstructor
public final class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteByTitle(String title) {
        repository.deleteByTitle(title);
    }

    @Override
    public void add(Project project) {
        repository.add(project);
    }

    @Override
    public double getFullTime(Project project) {

        double fullTime = 0;

        for(Sprint sprint : project.getSprints()) {
            fullTime += sprint.getElapsedTime();
        }

        return fullTime;
    }
}

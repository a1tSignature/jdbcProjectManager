package di.com.a1tSign.projectManager.service.project;

import di.com.a1tSign.projectManager.project.Project;
import di.com.a1tSign.projectManager.repository.project.ProjectRepository;
import di.com.a1tSign.projectManager.repository.project.ProjectRepositoryImpl;
import di.com.a1tSign.projectManager.sprint.Sprint;
import di.org.springframework.beans.factory.annotation.Autowired;
import di.org.springframework.beans.factory.stereotype.Service;
import di.org.springframework.beans.factory.stereotype.priority.Primary;

import java.util.List;

@SuppressWarnings("unused")
@Service
@Primary
public final class ProjectServiceImpl implements ProjectService {

    public ProjectServiceImpl() {
    }

    @Autowired
    private ProjectRepository projectRepositoryImpl;

    public void setProjectRepositoryImpl(ProjectRepositoryImpl projectRepositoryImpl) {
        this.projectRepositoryImpl = projectRepositoryImpl;
    }

    @Override
    public List<Project> findAll() {
        return projectRepositoryImpl.findAll();
    }

    @Override
    public void deleteByTitle(String title) {
        projectRepositoryImpl.deleteByTitle(title);
    }

    @Override
    public void add(Project project) {
        projectRepositoryImpl.add(project);
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

package duo.cmr.dysha.boundedContexts.projects.muster.repositories;

import duo.cmr.dysha.boundedContexts.projects.muster.projectsForms.Project;

import java.util.List;

public interface ProjectRepository {
    Project findByName(String projectName);

    void save(Project project);

    List<Project> alle();
}

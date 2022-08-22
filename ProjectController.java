package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.ProjectDto;
import nl.jvb.mypaintpholio.domain.entities.Project;
import nl.jvb.mypaintpholio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> allProjects = projectService.getAllProjects();
        return ResponseEntity.ok().body(allProjects);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByPersonId(@PathVariable("username") String username) {
        List<ProjectDto> userProjects;
        userProjects = projectService.getAllProjectsByPerson(username);
        return ResponseEntity.ok().body(userProjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
        ProjectDto oneProject = projectService.getProjectById(id);
        return ResponseEntity.ok().body(oneProject);
    }

    @PostMapping("/add_project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto newProject = projectService.createProject(projectDto);
//        final URI location = URI.create("/projects/" + newProject.getId());
//        final URI location = URI.create("/users/" + newProject.getUserId() + "/" + newProject.getId());
        return ResponseEntity.ok().body(newProject);
    }

    @PutMapping("/{id}/{username}")
    public void assignProjectToPerson(@PathVariable("id") Long id, @PathVariable("username") String username) {
        projectService.assignProjectToPerson(id, username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") Long id,
                                                   @RequestBody ProjectDto projectDto) {
        ProjectDto dto = projectService.updateProject(id, projectDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
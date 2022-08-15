package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholiobe.domain.dtos.ProjectUpdateDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.domain.entities.ProjectUpdate;
import nl.jvb.mypaintpholiobe.services.ProjectUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class ProjectUpdateController {

    private final ProjectUpdateService projectUpdateService;
    private final FileUploadController fileUploadController;

    @Autowired
    public ProjectUpdateController(ProjectUpdateService projectUpdateService,
                                   FileUploadController fileUploadController) {
        this.projectUpdateService = projectUpdateService;
        this.fileUploadController = fileUploadController;
    }

    @GetMapping("/updates")
    public ResponseEntity<List<ProjectUpdateDto>> getAllProjectUpdates() {
        List<ProjectUpdateDto> allProjectUpdates = projectUpdateService.getAllUpdates();
        return ResponseEntity.ok().body(allProjectUpdates);
    }
// NOG GOED NADENKEN OVER HET PAD VAN DEZE QUERY!!! <------------------------------
    @GetMapping("/{project_id}/projects")
    public ResponseEntity<List<ProjectUpdateDto>> getAllUpdatesByProjectId(@RequestParam(value = "projectId") Long Id) {
        List<ProjectUpdateDto> projectUpdates;
        projectUpdates = projectUpdateService.getAllUpdatesByProjectId(Id);
        return ResponseEntity.ok().body(projectUpdates);
    }

    @GetMapping("/updates/{id}")
    public ResponseEntity<ProjectUpdateDto> getProjectUpdateById(@PathVariable("id") Long id) {
        ProjectUpdateDto oneUpdate = projectUpdateService.getUpdateById(id);
        return ResponseEntity.ok().body(oneUpdate);
    }
    // NOG GOED NADENKEN OVER HET PAD VAN DEZE QUERY!!! <------------------------------
    @PostMapping("/add_update/{project_id}")
    public ResponseEntity<ProjectUpdateDto> createUpdate(@RequestBody ProjectUpdateDto updateDto,
                                                         @PathVariable ("project_id") Long projectId) {
        final ProjectUpdateDto newUpdate = projectUpdateService.createProjectUpdate(updateDto, projectId);
        final URI location = URI.create("/updates/" + newUpdate.getId());
        return ResponseEntity.created(location).body(newUpdate);
    }

    @PutMapping("/updates/{id}")
    public ResponseEntity<Object> updateProjectUpdate(@PathVariable("id") Long id,
                                              @RequestBody ProjectUpdateDto projectUpdateDto) {
        ProjectUpdateDto update = projectUpdateService.updateProjectUpdate(id, projectUpdateDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/updates/{id}")
    public ResponseEntity<ProjectUpdate> deleteProjectUpdate(@PathVariable("id") Long id) {
        projectUpdateService.deleteUpdateById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/updates/{id}/photo")
    public void assignPhotoToProjectUpdate(@PathVariable("id") Long updateId,
                                  @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        projectUpdateService.assignPhotoToProjectUpdate(photo.getFileName(), updateId);
    }
}

package ch.wiss.m426.controller;

import ch.wiss.m426.model.Project;
import ch.wiss.m426.repository.ProjectRepository;
import ch.wiss.m426.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ch.wiss.m426.SpringM426Application;
import ch.wiss.m426.model.Project.Status.*;

import javax.validation.Valid;

import static ch.wiss.m426.model.Project.Status.*;

@CrossOrigin()
@RestController
@RequestMapping(path = "/project/")
public class ProjectController {
    SpringM426Application application;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<String> addProject(@Valid @RequestBody Project project){
        projectRepository.save(project);
        return ResponseEntity.status(200).body("Project was successfully added");
    }
    @GetMapping(path = "")
    public @ResponseBody Iterable<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    @GetMapping(path = "/active/")
    public @ResponseBody Iterable<Project> getAllActiveProjects(){
        return projectRepository.findByStatus(ACTIVE);
    }

    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @PutMapping(path = "/update")
    @ResponseBody
    public ResponseEntity<String> updateProject(@RequestBody Project newProject){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Project project = projectRepository.findById(newProject.id);
        String coach = auth.getName();
        if(project.getCoach().equals(coach)){
            projectRepository.save(newProject);
            return ResponseEntity.status(200).body("Project was successfully updated");
        }

        project.setName(newProject.getName());
        project.setDescription(newProject.getDescription());
        project.setDate(newProject.getDate());
        project.setStatus(newProject.getStatus());
        projectRepository.save(project);

        return ResponseEntity.accepted().body("Thank you " + coach + " for signing up to coach " + project.getName());
    }
}
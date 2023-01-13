package ch.wiss.m426.repository;

import ch.wiss.m426.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByStatus(Project.Status status);
    Project findById(long id);
}
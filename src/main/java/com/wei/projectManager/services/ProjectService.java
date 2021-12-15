package com.wei.projectManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wei.projectManager.models.Project;
import com.wei.projectManager.repositories.ProjectRepo;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepo projectRepo;
	
// CRUD
	
	// READ ALL
	public List<Project> findProjects() {
		return projectRepo.findAll();
	}
	
	// READ ONE
	public Project findProject(Long projectId) {
		Optional<Project> optionalProject = projectRepo.findById(projectId);
		
		if (optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			return null;
		}
	}
	
	// CREATE and UPDATE ONE
	public Project createProject(Project project) {
		return projectRepo.save(project);
	}
	
	// DELETE ONE
	public void deleteProject(Long projectId) {
		projectRepo.deleteById(projectId);
	}
}

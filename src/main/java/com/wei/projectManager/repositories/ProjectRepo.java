package com.wei.projectManager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wei.projectManager.models.Project;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {
	List<Project> findAll();
}

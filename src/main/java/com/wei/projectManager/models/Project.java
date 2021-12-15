package com.wei.projectManager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Project Title is required!")
	@Size(min = 2, max = 30, message = "Project Title must be between 2 and 30 characters")
	private String title;
	
	@NotNull(message = "Project Description is required!")
	@Size(min = 2, max = 255, message = "Project Description must be between 2 and 30 characters")
	private String description;

	@Future(message = "Due date must be provided")
	private Date dueDate;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	// MANY to ONE --------- MANY to ONE --------- MANY to ONE --------- MANY to ONE
	// MANY projects could be by ONE user 
	@NotNull(message = "creator missing")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatorId")
	private User creatorId;
	
	// MANY to MANY --------- MANY to MANY --------- MANY to MANY --------- MANY to MANY
	// Projects could be by in team (multiple users) 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "projects_users",
	joinColumns = @JoinColumn(name = "project_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> teamUsers;
	
	public Project() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(User creatorId) {
		this.creatorId = creatorId;
	}

	public List<User> getTeamUsers() {
		return teamUsers;
	}

	public void setTeamUsers(List<User> teamUsers) {
		this.teamUsers = teamUsers;
	};
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}

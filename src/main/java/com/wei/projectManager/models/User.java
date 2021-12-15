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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "First Name is required!")
	@Size(min = 2, max = 30, message = "First Name must be between 2 and 30 characters")
	private String firstName;

	@NotNull(message = "Last name is required!")
	@Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
	private String lastName;

	@NotNull(message = "Email is required!")
	@Size(min = 2, max = 255, message = "Please enter a valid email!")
	private String email;

	@NotNull(message = "Password is required!")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;

	@Transient
	@NotNull(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Password must be same as Password")
	private String confirmPassword;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	// ONE to MANY ------ ONE to MANY ------ ONE to MANY ------ ONE to MANY ------
	// ONE user could create MANY projects
	@OneToMany(mappedBy = "creatorId", fetch = FetchType.LAZY)
	private List<Project> createdProjects;
	
	// MANY to MANY --------- MANY to MANY --------- MANY to MANY --------- MANY to MANY
	// Projects could be by in team (multiple users) 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "projects_users",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> joinedProjects;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Project> getCreatedProjects() {
		return createdProjects;
	}

	public void setCreatedProjects(List<Project> createdProjects) {
		this.createdProjects = createdProjects;
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

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}

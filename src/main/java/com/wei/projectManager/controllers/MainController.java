package com.wei.projectManager.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.wei.projectManager.models.LoginUser;
import com.wei.projectManager.models.Project;
import com.wei.projectManager.models.User;
import com.wei.projectManager.services.ProjectService;
import com.wei.projectManager.services.UserService;

@Controller
public class MainController {
	@Autowired
	private ProjectService proService;

	@Autowired
	private UserService userService;

// ------------------------------------------------------------------------------------
	// ---- route for display both registration and login form -------- route for
	// display both registration and login form ----
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("user_id") != null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "/user/index.jsp";
	}

	// ---- POST request for register ---- ---- POST request for register ---- ----
	// POST request for register ----
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		userService.register(newUser, result);

		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "/user/index.jsp";
		}

		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";
	}

	// ---- POST request for log in ---- ---- POST request for log in ---- ---- POST
	// request for log in ----
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		User user = userService.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "/user/index.jsp";
		}

		session.setAttribute("user_id", user.getId());
		return "redirect:/dashboard";
	}

// ------------------------------------------------------------------------------------
	// to show all projects
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User user = userService.displayUser(userId);
			model.addAttribute("user", user);
		}

		List<Project> projects = proService.findProjects();
		model.addAttribute("projects", projects);

		return "/project/dashboard.jsp";
	}

// ------------------------------------------------------------------------------------
	// to create new project with POST request
	// 1. create the display route
	@GetMapping("/projects/new")
	public String displayProjectForm(@ModelAttribute("project") Project project, Model model, HttpSession session) {

		Long user_id = (Long) session.getAttribute("user_id");

		if (user_id == null) {
			return "redirect:/";
		}

		model.addAttribute("user", userService.displayUser(user_id));
		return "/project/new.jsp";
	}

	// 2. create the acutal POST request
	@PostMapping("/projects/new")
	public String sentProjectForm(@Valid @ModelAttribute("project") Project project, BindingResult result) {
		if (result.hasErrors()) {
			return "/project/new.jsp";

		} else {
			proService.createProject(project);
			return "redirect:/dashboard";
		}
	}

// ------------------------------------------------------------------------------------
	// log out
	@GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:/";
	}

// ------------------------------------------------------------------------------------
	// join the project team
	@GetMapping("/project/{id}/join")
	public String joinProject(@PathVariable("id") Long projectId, HttpSession session) {

		Project project = proService.findProject(projectId);
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userService.displayUser(user_id);

		if (project.getTeamUsers().contains(user) == false) {

			project.getTeamUsers().add(user);
			proService.createProject(project);

		}

		return "redirect:/dashboard";
	}

// ------------------------------------------------------------------------------------
	// leave the project team
	@GetMapping("/project/{id}/leave")
	public String leaveProject(
			@PathVariable("id") Long projectId,
			HttpSession session) {

		Project project = proService.findProject(projectId);
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userService.displayUser(user_id);

		if (project.getTeamUsers().contains(user) == true) {

			project.getTeamUsers().remove(user);
			proService.createProject(project);

		}

		return "redirect:/dashboard";
	}
	
// ------------------------------------------------------------------------------------
	// render the project detail page
	@GetMapping("/projects/{id}")
	public String findProject(@PathVariable("id") Long projectId,
			Model model,
			HttpSession session) {
		
		Project pj = proService.findProject(projectId);
		model.addAttribute("project",pj);
		
		return "/project/show.jsp";
	}

// ------------------------------------------------------------------------------------
	// render the project form with info for edit
	@GetMapping("/project/{id}/edit")
	public String displayProjectEditForm(
			@ModelAttribute("project") Project project,
			HttpSession session,
			Model model
			) {
		
		Project theProject = proService.findProject(project.getId());
		
		if (session.getAttribute("user_id").equals(theProject.getCreatorId().getId())) {
			model.addAttribute("project" ,theProject);
			return "/project/edit.jsp";
		}
		return null;
	}
	
	// POST route to update the db base
	@PutMapping("/project/{id}/edit")
	public String editProject(
			@Valid @ModelAttribute("project") Project project, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(project.getCreatorId());
			return "project/edit.jsp";
		} else {
			proService.createProject(project);
			return "redirect:/dashboard";
		}
	}
	
// ------------------------------------------------------------------------------------
	// extra helper function for date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
}

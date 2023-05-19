package com.example.spring_boot_hibernate.controller;

import com.example.spring_boot_hibernate.UserService.UserService;
import com.example.spring_boot_hibernate.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Validated
@Controller

public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "index";
	}

	@RequestMapping(value = "/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping(value = "/create")
	public String create(@ModelAttribute("user")  User user,
						 BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "new";

		userService.addUser(user);
		return "redirect:/";
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show( Model model , @RequestParam  Long id ) {
		model.addAttribute("user", userService.getById(id));
		return "showUser";
	}
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Model model,
			@RequestParam(value = "id", defaultValue = "1") Long id ) {
		model.addAttribute("user", userService.getById(id));
		return "edit";
	}

	@PostMapping(value = "/delete")
	@ResponseBody
	public RedirectView delete( @RequestParam(value = "id", defaultValue = "1") Long id) {
		userService.deleteUser(id);
		return new RedirectView("/");
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public RedirectView edit(
						   @ModelAttribute("user")  User user,
					       @RequestParam(value = "id") Long id) {
		userService.changeDataUser(id,user);
		return new RedirectView("/");
	}

}
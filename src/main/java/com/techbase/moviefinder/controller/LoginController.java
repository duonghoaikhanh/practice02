package com.techbase.moviefinder.controller;

import com.techbase.moviefinder.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Login controller
 */
@Controller
public class LoginController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, Model model) {
		String filePath = "database/users.txt";
		String name = "";

		// read file
		ArrayList<User> users = readFile(filePath);

		if (username.isEmpty() && password.isEmpty()) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMessage", "Please input username and password!!!");
			return "login";
		}

		// Login
		for (User user : users){
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				name = user.getUsername();
				break;
			}
		}

		if (name.isEmpty()) {
			model.addAttribute("user", new User());
			model.addAttribute("errorMessage", "Please input correct username or password!!");
			return "login";
		}

		model.addAttribute("user", name);
		return "welcome";
	}

	/**
	 * Read file text about info user login
	 * @param fileName
	 * @return
	 */
	public static ArrayList<User> readFile(String fileName) {
		ArrayList<User> users = new ArrayList<>();
		String content = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));

			while ((content = in.readLine()) != null) {
				String[] userInfo = content.split("/");
				if (userInfo.length == 2) {
					User user = new User(userInfo[0], userInfo[1]);
					users.add(user);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}
}

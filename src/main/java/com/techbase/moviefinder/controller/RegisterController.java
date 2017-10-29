package com.techbase.moviefinder.controller;

import com.techbase.moviefinder.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class RegisterController {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, Model model) {
		String filePath = "database/users.txt";

		User user = new User(username, password);

		// Write to file
		writeFile(filePath, user);

		model.addAttribute("user", user);

		return "login";
	}

	/**
	 * Function write data to file
	 * @param fileName
	 * @param user
	 */
	public static void writeFile(String fileName, User user) {
		if (user != null) {
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(fileName, true));
//				writer.newLine();
				writer.append(user.getUsername() + "/" + user.getPassword());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.bus.controller;
import com.bus.entity.User;
import com.bus.payload.UserDTO;
import com.bus.repository.UserRepository;
import com.bus.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        userRepository.save(newUser);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {
        // Check username and password validity
        if (isValidLogin(username, password)) {
            // Successful login logic
            return "redirect:/welcome"; // Redirect to dashboard or desired page after successful login
        } else {
            // Invalid login, redirect back to login page with error message
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login";
        }
    }

    private boolean isValidLogin(String username, String password) {
        // Simulated user database
        Map<String, String> userDatabase = new HashMap<>();
        userDatabase.put("user1", "password1");
        userDatabase.put("user2", "password2");
        userDatabase.put("user3", "password3");

        // Check if the username exists in the database
        if (userDatabase.containsKey(username)) {
            // Compare the stored password with the provided password
            String storedPassword = userDatabase.get(username);
            return storedPassword.equals(password);
        }

        return false; // Username not found or password doesn't match
    }


    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        model.addAttribute("user", new User());
        return "delete";
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        try {
            userService.deleteUser(id);
            modelAndView.addObject("successMessage", "User deleted successfully.");
        } catch (UsernameNotFoundException ex) {
            modelAndView.addObject("errorMessage", ex.getMessage());
        }
        return modelAndView;
    }
}


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


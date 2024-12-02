package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.PlayerProfile;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.UserService;
import com.sash.volleyballApp.services.PlayerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerProfileService playerProfileService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("playerProfile", new PlayerProfile());
        model.addAttribute("error", null); // Default error attribute
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @ModelAttribute PlayerProfile playerProfile, Model model) {
        try {
            // Attempt to register user and profile
            userService.registerUserWithProfile(user, playerProfile);
            return "redirect:/dashboard"; // Redirect to login on success
        } catch (IllegalArgumentException e) {
            // Add error message to the model and reload the form
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("error", null); // Default error attribute
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            User user = userService.authenticate(username, password); // Add your login logic here
            return "redirect:/dashboard"; // Redirect to the dashboard on success
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

}

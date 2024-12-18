package com.sash.volleyballApp.controllers;

import com.sash.volleyballApp.models.Group;
import com.sash.volleyballApp.models.User;
import com.sash.volleyballApp.services.GroupService;
import com.sash.volleyballApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String showCreateGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "create-group";
    }

    @PostMapping("/create")
    public String createGroup(@RequestParam String name, @RequestParam String description) {
        User currentUser = userService.getLoggedInUser();
        groupService.createGroup(name, description, currentUser);
        return "redirect:/groups";
    }

    @GetMapping
    public String listUserGroups(Model model) {
        User currentUser = userService.getLoggedInUser();
        model.addAttribute("groups", groupService.getGroupsCreatedByUser(currentUser));
        return "group-list";
    }
}


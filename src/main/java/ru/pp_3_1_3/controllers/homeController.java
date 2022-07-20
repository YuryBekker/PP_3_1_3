package ru.pp_3_1_3.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pp_3_1_3.models.User;
import ru.pp_3_1_3.services.RoleService;
import ru.pp_3_1_3.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class homeController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public homeController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/")
    public String index(Principal principal, ModelMap model){
        User currentUser = userService.findUserByName(principal.getName());

        boolean isAdmin = userService.userHaveRole(principal.getName(), "ADMIN");

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("roles", roleService.roles());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.users());
        return "index";
    }
    @GetMapping("/profile")
    public String profile(Principal principal, ModelMap model){
        boolean isAdmin = userService.userHaveRole(principal.getName(), "ADMIN");
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", user);
        return "profile";
    }
}

package ru.pp_3_1_3.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pp_3_1_3.models.User;
import ru.pp_3_1_3.services.RoleService;
import ru.pp_3_1_3.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class adminController {
    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public adminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostMapping("/admin/create")
    public String create(User user){
        userService.create(user);
        return "redirect:/";
    }
    @PostMapping("/admin/edit")
    public String edit(int id, User user) {
        userService.edit(id, user);
        return "redirect:/";
    }
    @PostMapping("/admin/delete")
    public String delete(int id) {
        userService.delete(id);
        return "redirect:/";
    }
}

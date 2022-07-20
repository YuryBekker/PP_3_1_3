package ru.pp_3_1_3.services;


import ru.pp_3_1_3.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> roles();
    Role role(String name);
}

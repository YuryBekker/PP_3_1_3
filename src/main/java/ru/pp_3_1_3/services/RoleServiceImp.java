package ru.pp_3_1_3.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pp_3_1_3.models.Role;
import ru.pp_3_1_3.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    final
    RoleRepository repository;
    @Autowired
    public RoleServiceImp(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> roles() {
        return repository.findAll();
    }

    @Override
    public Role role(String name) {
        return repository.findRoleByName(name);
    }

}

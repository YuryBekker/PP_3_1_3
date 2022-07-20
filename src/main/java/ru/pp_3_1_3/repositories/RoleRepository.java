package ru.pp_3_1_3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pp_3_1_3.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(String name);

}

package com.mentorship.food_delivery.repository.user;

import com.mentorship.food_delivery.model.other.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRoleNameIn(List<String> name);
}
package com.proteech.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proteech.login.model.Role;
import com.proteech.login.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository {
Optional  findByName(RoleName roleName);
}

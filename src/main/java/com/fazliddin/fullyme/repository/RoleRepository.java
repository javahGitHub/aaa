package com.fazliddin.fullyme.repository;

import com.fazliddin.fullyme.entity.Role;
import com.fazliddin.fullyme.enums.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleTypeEnum roleType);


}

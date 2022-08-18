package com.sid.secservice.repository;

import com.sid.secservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppRoleRepository  extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);

}

package com.sid.secservice.repository;

import com.sid.secservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    void deleteByUsername(String username);
}

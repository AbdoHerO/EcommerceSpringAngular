package com.sid.secservice.services;

import com.sid.secservice.entities.Role;
import com.sid.secservice.entities.User;
import com.sid.secservice.repository.AppRoleRepository;
import com.sid.secservice.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private PasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository , AppRoleRepository appRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public User saveUser(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return appRoleRepository.save(role);
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = appUserRepository.findByUsername(username);
        Role role = appRoleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        User user = appUserRepository.findByUsername(username);
        Role role = appRoleRepository.findByRoleName(roleName);
        user.getRoles().remove(role);
    }

    @Override
    public void deleteUser(String username) {
        appUserRepository.deleteByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) {

        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<User> listUsers() {
        return appUserRepository.findAll();
    }
}

package com.sid.secservice.services;

import com.sid.secservice.entities.Role;
import com.sid.secservice.entities.User;

import java.util.List;

public interface AccountService {
    //public User saveUser(String username, String password,String ConfirmPassword);
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addRoleToUser(String username, String roleName);
    public void removeRoleFromUser(String username, String roleName);
    public void deleteUser(String username);
    public User loadUserByUsername(String username);
    public List<User> listUsers();
}

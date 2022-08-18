package com.sid.secservice.web;


import com.sid.secservice.dto.RoleUserForm;
import com.sid.secservice.entities.Role;
import com.sid.secservice.entities.User;
import com.sid.secservice.services.AccountService;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/users")
    @PostAuthorize("hasAuthority('USER')")
    public List<User> listUsers() {
        return accountService.listUsers();
    }

    @PostMapping("/register")
    @PostAuthorize("hasAuthority('ADMIN')")
    public User saveUser(@RequestBody User user) {
        return accountService.saveUser(user);
    }

    @PostMapping("/roles")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Role saveRole(@RequestBody Role role) {
        return accountService.saveRole(role);
    }

    @PostMapping("/addRoleToUser")
    @PostAuthorize("hasAuthority('ADMIN')")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpRequest request, HttpRequest response) {
//        ...
    }

}

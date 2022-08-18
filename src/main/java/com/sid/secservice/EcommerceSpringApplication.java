package com.sid.secservice;

import com.sid.secservice.entities.Role;
import com.sid.secservice.entities.User;
import com.sid.secservice.repository.AppUserRepository;
import com.sid.secservice.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // enable global method security , securedEnabled = true enables the @Secured annotation , prePostEnabled = true enables the @PreAuthorize and @PostAuthorize annotations
public class EcommerceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSpringApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner runner (AccountService accountService){
//		return args -> {
//			// save roles
//			accountService.saveRole(new Role(null,"ADMIN"));
//			accountService.saveRole(new Role(null,"USER"));
//			accountService.saveRole(new Role(null,"CUSTOMER_MANAGER"));
//			accountService.saveRole(new Role(null,"PRODUCT_MANAGER"));
//			accountService.saveRole(new Role(null,"BILLS_MANAGER"));
//
//			// save users
////			Stream.of("user1","user2","user3","user4","user5","user6","user7","user8","user9","user10")
////			.forEach(username -> {
////				User user = new User(null,username,"1234",true,new ArrayList<>());
////				accountService.saveUser(user);
////			});
//			accountService.saveUser(new User(null,"user1","1234",true,new ArrayList<>()));
//			accountService.saveUser(new User(null,"user2","1234",true,new ArrayList<>()));
//			accountService.saveUser(new User(null,"user3","1234",true,new ArrayList<>()));
//			accountService.saveUser(new User(null,"user4","1234",true,new ArrayList<>()));
//			accountService.saveUser(new User(null,"admin","1234",true,new ArrayList<>()));
//
//			// Affect Role to User
//			accountService.addRoleToUser("user1","USER");
//			accountService.addRoleToUser("admin","USER");
//			accountService.addRoleToUser("admin","ADMIN");
//			accountService.addRoleToUser("user2","USER");
//			accountService.addRoleToUser("user2","CUSTOMER_MANAGER");
//			accountService.addRoleToUser("user3","USER");
//			accountService.addRoleToUser("user3","PRODUCT_MANAGER");
//			accountService.addRoleToUser("user4","USER");
//			accountService.addRoleToUser("user4","BILLS_MANAGER");
//
//		};
//	}

}

package io.getarrays.userservice;

import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.domain.UserRole;
import io.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveUserRole(new UserRole(null, "ROLE_USER"));
			userService.saveUserRole(new UserRole(null, "ROLE_MANAGER"));
			userService.saveUserRole(new UserRole(null, "ROLE_ADMIN"));
			userService.saveUserRole(new UserRole(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Aarya Chaudhary", "arya", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Aman Chaudhary", "aman", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Ranjita Chaudhary", "ranjita", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Sanju Chaudhary", "sanju", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Amrita Chaudhary", "amrita", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Aayusha Chaudhary", "ayusa", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Aaisa Chaudhary", "aisa", "12345", new ArrayList<>()));

			userService.addRoleToUser("arya", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arya", "ROLE_ADMIN");
			userService.addRoleToUser("arya", "ROLE_MANAGER");
			userService.addRoleToUser("aman", "ROLE_ADMIN");
			userService.addRoleToUser("aman", "ROLE_MANAGER");
			userService.addRoleToUser("sanju", "ROLE_MANAGER");
			userService.addRoleToUser("sanju", "ROLE_ADMIN");
			userService.addRoleToUser("amrita", "ROLE_ADMIN");
			userService.addRoleToUser("amrita", "ROLE_USER");

		};
	}

}

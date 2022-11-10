package io.getarrays.userservice.api;

import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.domain.UserRole;
import io.getarrays.userservice.param.AddRoleUserParam;
import io.getarrays.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping(value = "users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @PostMapping(value = "save/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("save/user").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping(value = "save/role")
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("save/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUserRole(role));
    }

    @PostMapping(value = "role/adduser")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleUserParam role) {
        userService.addRoleToUser(role.getUsername(), role.getRole());
        return ResponseEntity.ok().build();
    }
}


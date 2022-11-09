package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.domain.UserRole;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    UserRole saveUserRole(UserRole userRole);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getAllUser();
}

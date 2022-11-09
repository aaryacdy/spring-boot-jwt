package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.domain.UserRole;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.debug("Saving new user to the database.");
        return userRepo.save(user);
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        log.debug("Saving new role to database {} "+userRole.getName());
        return roleRepo.save(userRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.debug("Add new role {} to user {}", username, roleName);
        User user = userRepo.findByUsername(username);
        UserRole userRole = roleRepo.findByName(roleName);
        user.getRole().add(userRole);
    }

    @Override
    public User getUser(String username) {
        log.debug("Fetching user {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        log.debug("Fetching all users.");
        return userRepo.findAll();
    }
}

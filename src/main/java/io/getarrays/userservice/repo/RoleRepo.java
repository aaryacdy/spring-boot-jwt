package io.getarrays.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import io.getarrays.userservice.domain.UserRole;

public interface RoleRepo extends JpaRepository<UserRole,  Long> {
    UserRole findByName(String name);
}

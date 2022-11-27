package com.example.hedgenet_backend.Repository;

import com.example.hedgenet_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    public Optional<User> findByUsername(String user_name);
}

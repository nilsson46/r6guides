package com.example.r6guides.repository;

import com.example.r6guides.models.Operator;
import com.example.r6guides.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Operator> findByUsername(String username);
}

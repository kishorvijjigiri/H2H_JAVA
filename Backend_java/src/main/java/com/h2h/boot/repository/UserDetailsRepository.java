package com.h2h.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.h2h.boot.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    
    Optional<UserDetails> findByEmail(String email); // Optional for safer null handling

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}

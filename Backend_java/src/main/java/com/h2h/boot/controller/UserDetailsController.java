package com.h2h.boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.h2h.boot.model.UserDetails;
import com.h2h.boot.service.UserDetailsService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/userdetails")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    // Save user details
    @PostMapping("/save")
    public ResponseEntity<UserDetails> saveUser(@Valid @RequestBody UserDetails userDetails) {
        UserDetails savedUser = userDetailsService.saveUserDetails(userDetails);
        return ResponseEntity.ok(savedUser);
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmail(@PathVariable("email") String email) {
        boolean exists = userDetailsService.isEmailRegistered(email);
        return ResponseEntity.ok(exists);
    }

    // Get user by email
    @GetMapping("/get/{email}")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email) {
        UserDetails user = userDetailsService.getUserByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Update user by email
    @PutMapping("/update/{email}")
    public ResponseEntity<UserDetails> updateUserByEmail(
            @PathVariable String email,
            @Valid @RequestBody UserDetails updatedUserDetails) {
        UserDetails updatedUser = userDetailsService.updateUserByEmail(email, updatedUserDetails);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // Delete user by email
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userDetailsService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    // Get all users
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        List<UserDetails> users = userDetailsService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDetails loginDetails) {
        UserDetails user = userDetailsService.authenticateUser(loginDetails.getEmail(), loginDetails.getPassword());
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(401).body("Invalid email or password");
    }
}

package com.h2h.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2h.boot.model.UserDetails;
import com.h2h.boot.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails getUserByEmail(String email) {
        return userDetailsRepository.findByEmail(email).orElse(null);
    }

    public boolean isEmailRegistered(String email) {
        return userDetailsRepository.existsByEmail(email);
    }

    public UserDetails updateUserByEmail(String email, UserDetails updatedUserDetails) {
        Optional<UserDetails> existingUserOptional = userDetailsRepository.findByEmail(email);
        
        if (existingUserOptional.isPresent()) {
            UserDetails existingUser = existingUserOptional.get();
            existingUser.setFullName(updatedUserDetails.getFullName());
            existingUser.setMobileNo(updatedUserDetails.getMobileNo());
            existingUser.setAadhaarNo(updatedUserDetails.getAadhaarNo());
            existingUser.setBloodGroup(updatedUserDetails.getBloodGroup());
            existingUser.setCountry(updatedUserDetails.getCountry());
            existingUser.setState(updatedUserDetails.getState());
            existingUser.setDistrict(updatedUserDetails.getDistrict());
            existingUser.setCityOrVillage(updatedUserDetails.getCityOrVillage());
            existingUser.setPinCode(updatedUserDetails.getPinCode());
            existingUser.setPassword(updatedUserDetails.getPassword());
            existingUser.setConformPassword(updatedUserDetails.getConformPassword());
            
            return userDetailsRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUserByEmail(String email) {
        userDetailsRepository.deleteByEmail(email);
    }

    public List<UserDetails> getAllUsers() {
        return userDetailsRepository.findAll();
    }

    public UserDetails authenticateUser(String email, String password) {
        return userDetailsRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }
}

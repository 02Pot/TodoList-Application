package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.model.AppUser;
import com.todolist.repo.UserRepo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
public class UserController {
    
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/page/u_register")
    public AppUser createUser(@Valid @NotNull @RequestBody AppUser user){
        String hashedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(hashedPassword);
        return userRepo.save(user);
    }

    // @PostMapping("/page/u_login")
    // public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
    //     String email = loginData.get("userEmail");
    //     String password = loginData.get("userPassword");

    //     Optional<AppUser> optionalUser = userRepo.findByUserEmail(email);
    //     if (optionalUser.isPresent()) {
    //         AppUser user = optionalUser.get();
    //         if (passwordEncoder.matches(password, user.getUserPassword())) {
    //             return ResponseEntity.ok(user);
    //         } else {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    //         }
    //     } else {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    //     }
    // }

    /*
    @PutMapping(value = "/{id}")
    public AppUser update(@PathVariable Long id, @RequestBody AppUser user) {
        AppUser existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserFirstName(user.getUserFirstName());
        existingUser.setUserLastName(user.getUserLastName());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserPassword(user.getUserPassword());
        return userRepo.save(existingUser);
    } */

    /*
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepo.deleteById(id);
    }*/
    
}

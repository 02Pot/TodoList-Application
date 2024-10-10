package com.todolist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.model.AppUser;
import com.todolist.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    @Autowired
    private final UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        System.out.println("Searching for user with email: " + userEmail);
        Optional<AppUser> user = userRepo.findByUserEmail(userEmail);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUserEmail())
                    .password(userObj.getUserPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException(userEmail);
        }
    }


}

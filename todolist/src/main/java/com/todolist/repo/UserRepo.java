package com.todolist.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.model.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByUserEmail(String userEmail);
}

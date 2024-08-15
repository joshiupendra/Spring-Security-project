package com.seemay.springSec.repo;

import com.seemay.springSec.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
}

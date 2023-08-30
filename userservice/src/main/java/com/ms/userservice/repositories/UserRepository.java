package com.ms.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}

package com.accenture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.model.User;

@Repository
public interface SpringFileReadRepository extends JpaRepository<User, Integer>{

}

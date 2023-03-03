package com.nitin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitin.blog.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

}

package com.nitin.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitin.blog.entity.Category;

@Repository
public interface ICategoryRepo extends JpaRepository<Category,Integer> {

}

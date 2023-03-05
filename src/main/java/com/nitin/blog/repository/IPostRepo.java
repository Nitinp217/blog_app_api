package com.nitin.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitin.blog.entity.Category;
import com.nitin.blog.entity.Post;
import com.nitin.blog.entity.User;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
}

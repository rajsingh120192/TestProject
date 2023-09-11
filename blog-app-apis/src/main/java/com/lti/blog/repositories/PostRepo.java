package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.blog.entities.Post;
import java.util.List;
import com.lti.blog.entities.User;
import com.lti.blog.entities.Category;



@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}

package com.lti.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

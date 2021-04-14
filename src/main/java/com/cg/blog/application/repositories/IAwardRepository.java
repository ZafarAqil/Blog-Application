package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Award;

public interface IAwardRepository extends JpaRepository<Award, Integer>{

}

package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Admin;

/**
 * IAdminRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
public interface IAdminRepository extends JpaRepository<Admin, Integer>{

}

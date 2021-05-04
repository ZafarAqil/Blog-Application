package com.cg.blog.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.blog.application.entities.ERole;
import com.cg.blog.application.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}

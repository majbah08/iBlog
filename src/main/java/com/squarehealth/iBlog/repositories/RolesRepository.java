package com.squarehealth.iBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.squarehealth.iBlog.models.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(@Param("role") String role);

}

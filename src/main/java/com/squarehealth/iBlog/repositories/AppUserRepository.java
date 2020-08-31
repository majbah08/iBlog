package com.squarehealth.iBlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.squarehealth.iBlog.models.AppUsers;

@Repository
public interface AppUserRepository extends JpaRepository<AppUsers,Long> {
	
    Optional<AppUsers> findByEmail(@Param("email") String email);

    @Query("select u from AppUsers u where u.userName like %:username")
    Optional<AppUsers> findByUsername(@Param("username") String userName);


}

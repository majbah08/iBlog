package com.squarehealth.iBlog.services;

import java.util.Optional;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.helpers.ERoleNames;

public interface AppUserService {
	
    Optional<AppUsers> findByEmail(@Param("email") String email);
    Optional<AppUsers> findByUsername(@Param("username") String username);
    AppUsers save(AppUsers appusers);
	AppUsers save(AppUsers appusers, ERoleNames usertype);

}

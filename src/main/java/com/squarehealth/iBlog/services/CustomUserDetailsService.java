package com.squarehealth.iBlog.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.helpers.CustomUserDetails;
import com.squarehealth.iBlog.repositories.AppUserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = Logger.getLogger(CustomUserDetailsService.class);

	AppUserRepository userRepository;


    
    @Autowired
    public CustomUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUsers> dbUser = userRepository.findByUsername(username);
        log.info("Fetched user : " + dbUser + " by " + username);
        return dbUser.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find a matching user email in the database for " + username));
    }
	
	
	
}
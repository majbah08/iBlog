package com.squarehealth.iBlog.controllers.RestControllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.helpers.ApiResponse;
import com.squarehealth.iBlog.models.helpers.CustomUserDetails;
import com.squarehealth.iBlog.models.helpers.ERoleNames;
import com.squarehealth.iBlog.models.payloads.AuthenticationRequest;
import com.squarehealth.iBlog.services.AppUserService;
import com.squarehealth.iBlog.services.CustomUserDetailsService;
import com.squarehealth.iBlog.utils.JwtUtil;

@RestController
@RequestMapping("/beta/v1/auth")
public class AuthRestController {
	
    private static final Logger log = Logger.getLogger(AuthRestController.class);

	
	private AppUserService appUserService;
	
	private AuthenticationManager authenticationManager;

	private JwtUtil jwtTokenUtil;
	
	private CustomUserDetailsService userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	public AuthRestController(AppUserService appUserService, AuthenticationManager authenticationManager,
			JwtUtil jwtTokenUtil, CustomUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder
 ) {
		this.appUserService = appUserService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		HttpHeaders responseHeaders = new HttpHeaders();
			try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), bCryptPasswordEncoder.encode(authenticationRequest.getPassword())));

			//if authentication was succesful else throw an exception
			final CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			
			return  ResponseEntity.ok(new ApiResponse(true,jwt) );
			}catch(Exception e) {
				
		        return ResponseEntity.ok(new ApiResponse(false, "Login failed"));

				
				
				
			}
		}
	
	@PostMapping("/register")
	 public ResponseEntity<?> registration(@Valid @RequestBody AppUsers appuser) {
		
		try {
			
		log.info(appuser.toString());
		
		appUserService.save(appuser,ERoleNames.ROLE_BLOGGER);
		
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully. Check your email for verification"));
		}catch(Exception e) {
			
	        return ResponseEntity.ok(new ApiResponse(false, "User registration failed"));

			
		}
		
		
		
	}

}

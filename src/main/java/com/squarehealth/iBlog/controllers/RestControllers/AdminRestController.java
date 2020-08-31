package com.squarehealth.iBlog.controllers.RestControllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.helpers.ApiResponse;
import com.squarehealth.iBlog.models.helpers.ERoleNames;
import com.squarehealth.iBlog.services.AppUserService;

@RestController
@RequestMapping("/beta/v1/admin")

public class AdminRestController {
	
	
    private static final Logger log = Logger.getLogger(AdminRestController.class);

	private AppUserService appUserService;

	
	 @PostMapping("/addAdmin")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<?> adminRegistration(@Valid @RequestBody AppUsers appuser) {
		
		try {
			
		log.info(appuser.toString());
		
		appUserService.save(appuser,ERoleNames.ROLE_BLOGGER);
		
       return ResponseEntity.ok(new ApiResponse(true, "Admin registered successfully"));
		}catch(Exception e) {
			
	        return ResponseEntity.ok(new ApiResponse(false, "Admin registration failed"));

			
		}
	 }
	 
	 @PostMapping("/addAdmin")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public ResponseEntity<?> makeExistingUserAdmin(@Valid @RequestBody AppUsers appuser) {
		
		try {
			
		log.info(appuser.toString());
		
		appUserService.save(appuser,ERoleNames.ROLE_ADMIN);
		
       return ResponseEntity.ok(new ApiResponse(true, " User "+appuser.getUserName()+" promoted as Admin successfully"));
		}catch(Exception e) {
			
	        return ResponseEntity.ok(new ApiResponse(false, "Admin promotion failed"));

			
		}
	 }

	 
}

package com.squarehealth.iBlog.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.Roles;
import com.squarehealth.iBlog.models.helpers.ERoleNames;
import com.squarehealth.iBlog.repositories.AppUserRepository;
import com.squarehealth.iBlog.repositories.RolesRepository;

@Service
public class AppUserServiceImpl implements AppUserService {
	
	
	private AppUserRepository appUserRepo;

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private RolesRepository rolerepository;

	@Autowired
	public AppUserServiceImpl(AppUserRepository appUserRepo, BCryptPasswordEncoder bCryptPasswordEncoder,RolesRepository rolerepository) {
		super();
		this.appUserRepo = appUserRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.rolerepository=rolerepository;
	}


	
	@Override
	public Optional<AppUsers> findByEmail(String email) {
		// TODO Auto-generated method stub
		return appUserRepo.findByEmail(email);
	}

	@Override
	public Optional<AppUsers> findByUsername(String username) {
		// TODO Auto-generated method stub
		return appUserRepo.findByUsername(username);
	}

	@Override
	public AppUsers save(AppUsers appusers,ERoleNames usertype ) {
		Set <Roles> role = new HashSet();
		if(usertype.name().equals("ROLE_BLOGGER")) {
		Roles roleName = rolerepository.findByRole(ERoleNames.ROLE_BLOGGER.name());
		role.add(roleName);
		appusers.setRoles(role);

		appusers.setActive(false);
		}else {
			
			Roles roleName = rolerepository.findByRole(ERoleNames.ROLE_ADMIN.name());
			role.add(roleName);
			appusers.setRoles(role);

			appusers.setActive(true);

		}
		appusers.setPasswordHash(bCryptPasswordEncoder.encode(appusers.getPasswordHash()));
		return appUserRepo.save(appusers);
	}



	@Override
	public AppUsers save(AppUsers appusers) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.squarehealth.iBlog.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.squarehealth.iBlog.models.helpers.ERoleNames;

@Table
@Entity(name="role")
public class Roles {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "role_id")
	    private Long id;

		@Enumerated(EnumType.STRING)
	    @Column(name = "role", unique = true)
	    private ERoleNames role;

	    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	    private Collection<AppUsers> users;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	   
	    public ERoleNames getRole() {
			return role;
		}

		public void setRole(ERoleNames role) {
			this.role = role;
		}

		public Collection<AppUsers> getUsers() {
	        return users;
	    }

	    public void setUsers(Collection<AppUsers> users) {
	        this.users = users;
	    }

}

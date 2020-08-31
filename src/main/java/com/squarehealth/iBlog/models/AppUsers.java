package com.squarehealth.iBlog.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squarehealth.iBlog.validations.annotation.ValidUserName;
import com.sun.istack.NotNull;

@Entity
@Table(name="app_users")
public class AppUsers {
	 
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "user_id")
		private Long id;
		
		
		
		@Column(name="user_name",nullable = false, length = 30, unique = true)
		@NotEmpty(message = "Please provide your User Name")
		@ValidUserName
		private String userName;
		
		@Column(name="password_hash",length = 60)
		@Length(min = 5, message = "Your password must have at least 5 characters")
		@NotEmpty(message = "Please provide your password")
		@JsonIgnore
	
		private String passwordHash;
		
	    public Set<BlogPost> getPosts() {
			return posts;
		}


		public void setPosts(Set<BlogPost> posts) {
			this.posts = posts;
		}


		@Transient
	    private String passwordConfirm;

	 
		
		@Column(name="full_name",length = 100)
		@NotEmpty(message = "Please provide your full name")
		private String fullName;
		
		@Column
		@NotNull
		@Email
		private String email;
		
		@Column(name="is_active")
		@JsonIgnore
		private boolean isActive;
		
		
		@ManyToMany(fetch=FetchType.LAZY ,cascade = CascadeType.ALL)
	    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Roles> roles;
		

		@OneToMany( fetch=FetchType.LAZY,mappedBy = "user")
		private Set<BlogPost> posts = new HashSet<>();
		

		
		public AppUsers(AppUsers user) {
			// TODO Auto-generated constructor stub
		}


		public AppUsers() {
			// TODO Auto-generated constructor stub
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}

		@JsonIgnore
		public String getPasswordHash() {
			return passwordHash;
		}


		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}


		public String getPasswordConfirm() {
			return passwordConfirm;
		}


		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}


		public String getFullName() {
			return fullName;
		}


		public void setFullName(String fullName) {
			this.fullName = fullName;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public boolean isActive() {
			return isActive;
		}


		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}


		public Set<Roles> getRoles() {
			return roles;
		}


		public void setRoles(Set<Roles> roles) {
			this.roles = roles;
		}


		
		
		
		
		
	

}

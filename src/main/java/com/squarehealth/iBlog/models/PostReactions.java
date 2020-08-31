package com.squarehealth.iBlog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="post_reaction")
public class PostReactions {
	
	@Id
	private Long id ;
	@Column
	private String reactionName;
	@ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    @NotNull
    private BlogPost post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private AppUsers user;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReactionName() {
		return reactionName;
	}
	public void setReactionName(String reactionName) {
		this.reactionName = reactionName;
	}
	public BlogPost getPost() {
		return post;
	}
	public void setPost(BlogPost post) {
		this.post = post;
	}
	public AppUsers getUser() {
		return user;
	}
	public void setUser(AppUsers user) {
		this.user = user;
	}
		
	

}

package com.squarehealth.iBlog.controllers.RestControllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squarehealth.iBlog.exceptions.ResourceNotFoundException;
import com.squarehealth.iBlog.models.BlogPost;
import com.squarehealth.iBlog.models.helpers.ApiResponse;
import com.squarehealth.iBlog.services.AppUserService;
import com.squarehealth.iBlog.services.BlogPostService;


@RestController
@RequestMapping("/beta/v1/post")
public class PostRestController {
    
	private static final Logger log = Logger.getLogger(PostRestController.class);

	private final BlogPostService postService;
    private final AppUserService userService;

    @Autowired
    public PostRestController(BlogPostService postService, AppUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

	
	
	    @PostMapping( "/newPost")
	    public ResponseEntity createNewPost(@Valid @RequestBody BlogPost post,
	                                BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
                return ResponseEntity.ok(new ApiResponse(false,"Post created Successfully") );

               	        			 } else {
	            postService.save(post);
                return ResponseEntity.ok(new ApiResponse(true,"Post created Successfully") );
	        }
	    }

	

	    @GetMapping(value = "/post/{id}")
	    public ResponseEntity getPostWithId(@PathVariable Long id) {

	        Optional<BlogPost> optionalPost = postService.findForId(id);

	        if (optionalPost.isPresent()) {
	        	BlogPost post = optionalPost.get();


	        return ResponseEntity.ok(new ApiResponse(true,post) );


	        } else {
	        	throw new ResourceNotFoundException("Blog post not found with ","id ",id);
	        }
	    }

	    @GetMapping(value = "/allPost")
	    public ResponseEntity getAllPost(Principal principal) {
	    	try {
	        List<BlogPost> allPost = postService.findAll();
		return ResponseEntity.ok(new ApiResponse(true, allPost));
	    	} catch(Exception e){
	    		
	    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(true, "Something Went wrong"));

	    		
	    	}

	    }

	    @PutMapping("/post/{id}")
	    public ResponseEntity editPostWithId(@PathVariable Long id, @RequestBody BlogPost post ,
	                                   Principal principal) {

	        Optional<BlogPost> singlePost = postService.findForId(id);

	        if (singlePost.isPresent()) {

	            if (isPrincipalOwnerOfPost(principal, post)) {
	                postService.save(post);
	                return ResponseEntity.ok(new ApiResponse(true,"Post Deleted Successfully") );
	            } else {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false,"You are not allowed to delete this post ") );
	            }

	        } else {
	        	throw new ResourceNotFoundException("Blog post not found with ","id ",id);
	        }
	    }

	    

	    @DeleteMapping("/post/{id}")
	    public ResponseEntity deletePostWithId(@PathVariable Long id,
	                                   Principal principal) {

	        Optional<BlogPost> singlePost = postService.findForId(id);

	        if (singlePost.isPresent()) {
	        	BlogPost post = singlePost.get();

	            if (isPrincipalOwnerOfPost(principal, post)) {
	                postService.delete(post);
	                return ResponseEntity.ok(new ApiResponse(true,"Post Deleted Successfully") );
	            } else {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false,"You are not allowed to delete this post ") );
	            }

	        } else {
	        	throw new ResourceNotFoundException("Blog post not found with ","id ",id);
	        }
	    }

	    private boolean isPrincipalOwnerOfPost(Principal principal, BlogPost post) {
	        return principal != null && principal.getName().equals(post.getUser().getUserName());
	    }

}

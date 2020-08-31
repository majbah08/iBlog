package com.squarehealth.iBlog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.BlogPost;

public interface BlogPostService {
	
    Optional<BlogPost> findForId(Long id);


    BlogPost save(BlogPost post);

    /**
     * Finds a {@link Page) of {@link Post} of provided user ordered by date
     */
    Page<BlogPost> findByUserOrderedByDatePageable(AppUsers user, int page);

    /**
     * Finds a {@link Page) of all {@link Post} ordered by date
     */
    Page<BlogPost> findAllOrderedByDatePageable(int page);

    void delete(BlogPost post);
    
    List <BlogPost>findAll();
    

}

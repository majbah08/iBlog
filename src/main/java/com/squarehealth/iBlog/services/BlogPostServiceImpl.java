package com.squarehealth.iBlog.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.BlogPost;
import com.squarehealth.iBlog.repositories.BlogPostRepository;

@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	private final BlogPostRepository blogPostRepo;
	
	@Autowired
	public BlogPostServiceImpl(BlogPostRepository blogPostRepo) {
		super();
		this.blogPostRepo = blogPostRepo;
	}


	@Override
	public Optional<BlogPost> findForId(Long id) {
	
		return blogPostRepo.findById(id);
	}

	
	@Override
	public BlogPost save(BlogPost post) {
		return blogPostRepo.save(post);
	}

	@Override
	public Page<BlogPost> findByUserOrderedByDatePageable(AppUsers user, int page) {
		return blogPostRepo.findByUserOrderByCreateDateDesc(user,  PageRequest.of(subtractPageByOne(page),5));
	}

	@Override
	public Page<BlogPost> findAllOrderedByDatePageable(int page) {
		return blogPostRepo.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
	}

	@Override
	public void delete(BlogPost post) {
		blogPostRepo.delete(post);
	}

	@Override
	public List<BlogPost> findAll() {
		return blogPostRepo.findAll();
	}
	
	private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }


}

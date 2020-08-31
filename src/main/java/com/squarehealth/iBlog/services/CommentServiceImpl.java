package com.squarehealth.iBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squarehealth.iBlog.models.Comment;
import com.squarehealth.iBlog.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	   private final CommentRepository commentRepository;

	    @Autowired
	    public CommentServiceImpl(CommentRepository commentRepository) {
	        this.commentRepository = commentRepository;
	    }

	    @Override
	    public Comment save(Comment comment) {
	        return commentRepository.saveAndFlush(comment);
	    }
	

}

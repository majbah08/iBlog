package com.squarehealth.iBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squarehealth.iBlog.models.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

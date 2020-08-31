package com.squarehealth.iBlog.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.squarehealth.iBlog.models.AppUsers;
import com.squarehealth.iBlog.models.BlogPost;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
	Page<BlogPost> findByUserOrderByCreateDateDesc(AppUsers user, Pageable pageable);

    Page<BlogPost> findAllByOrderByCreateDateDesc(Pageable pageable);

    Optional<BlogPost> findById(Long id);

}

package com.blibli.qa.errorbook.repository;

import com.blibli.qa.errorbook.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByErrorTypeOrderByCreatedDate(String errorType);
}
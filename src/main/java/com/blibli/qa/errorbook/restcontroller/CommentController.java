package com.blibli.qa.errorbook.restcontroller;

import com.blibli.qa.errorbook.model.Comment;
import com.blibli.qa.errorbook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
      try {
        Comment _comment = commentRepository.save(new Comment(
                comment.getErrorType(),
                comment.getCreatedDate(),
                comment.getUsername(),
                comment.getComment()
        ));
        return new ResponseEntity<>(_comment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/comment/{errorType}")
    public ResponseEntity<List<Comment>> getListComment(@PathVariable("errorType") String errorType) {
        List<Comment> listComment = commentRepository.findByErrorTypeOrderByCreatedDate(errorType);
        return new ResponseEntity<>(listComment, HttpStatus.OK);
    }

    @PostMapping("/comment/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") String id, @RequestBody Comment comment) {
        Optional<Comment> commentData = commentRepository.findById(id);

        if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            _comment.setComment(comment.getComment());
            _comment.setUpdatedDate(LocalDateTime.now());
            return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/comment/detete/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

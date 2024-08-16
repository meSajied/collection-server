package org.collections.collections;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
  private final CommentRepository commentRepository;

  public CommentController(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @PostMapping("/")
  Comments addComment(@RequestBody Comments comment) {
    return commentRepository.save(comment);
  }
}

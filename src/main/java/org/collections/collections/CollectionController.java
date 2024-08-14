package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collections")
class CollectionController {
  private final CollectionService collectionService;
  private final CommentRepository commentRepository;

  CollectionController(CollectionService collectionService,
      CommentRepository commentRepository) {
    this.collectionService = collectionService;
    this.commentRepository = commentRepository;
  }

  @GetMapping("/latest")
  List<Collection> getLatestCollections() {
    return collectionService.getLatestCollections();
  }

  @GetMapping("/largest")
  List<Collection> getLargestCollections() {
    return collectionService.getLargestCollections();
  }

  @GetMapping("{id}")
  Optional<Collection> getCollectionById(@PathVariable String id) {
    return collectionService.getCollectionById(id);
  }

  @PostMapping("/")
  Collection createCollection(@RequestBody Collection collection) {
    return collectionService.createCollection(collection);
  }

  @PatchMapping("/")
  Optional<Collection> updateCollection(@RequestBody Collection collection) {
    return collectionService.updateCollection(collection);
  }

  @DeleteMapping("/{id}")
  void deleteCollectionOf(@PathVariable String id) {
    collectionService.deleteCollectionBy(id);
  }

  @PostMapping("/comments")
  Comments addComment(@RequestBody Comments comment) {
    return commentRepository.save(comment);
  }
}

package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collections")
class CollectionController {
  private final CollectionService collectionService;

  CollectionController(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @GetMapping("/latest")
  List<Collection> getLatestCollections() {
    return collectionService.getLatestCollections();
  }

  @GetMapping("/largest")
  List<Collection> getLargestCollections() {
    return collectionService.getLargestCollections();
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
}

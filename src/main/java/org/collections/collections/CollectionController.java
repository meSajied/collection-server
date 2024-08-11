package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
public class CollectionController {
  @Autowired
  CollectionService collectionService;

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/latest")
  public List<Collection> getLatestCollections() {
    return collectionService.getLatestCollections();
  }

  @GetMapping("/largest")
  public List<Collection> getLargestCollections() {
    return collectionService.getLargestCollections();
  }

  @PostMapping("/")
  public Collection createCollection(@RequestBody Collection collection) {
    return collectionService.createCollection(collection);
  }

  @PatchMapping("/")
  public Optional<Collection> updateCollection(@RequestBody Collection collection) {
    return collectionService.updateCollection(collection);
  }

  @DeleteMapping("/{id}")
  public void deleteCollectionOf(@PathVariable String id) {
    collectionService.deleteCollectionBy(id);
  }
}

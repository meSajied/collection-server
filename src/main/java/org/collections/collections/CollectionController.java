package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.collections.utils.FileUploader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

  @GetMapping("/{username}")
  Optional<Collection> getCollectionByUsername(@PathVariable String username) {
    return collectionService.getCollectionByUsername(username);
  }

  @PostMapping(value = "/")
  String createCollection(@RequestParam("file") MultipartFile file,
      @RequestPart Collection collection) {
    Collection saved = collectionService.createCollection(collection);

    FileUploader uploader = new FileUploader();
    String id = String.valueOf(saved.getId());

    return uploader.uploadFile(file, id);
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

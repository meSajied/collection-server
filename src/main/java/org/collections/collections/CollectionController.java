package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.collections.utils.FileUploader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/collections")
public class CollectionController {
  private final CollectionService collectionService;

  CollectionController(CollectionService collectionService) {
    this.collectionService = collectionService;
  }

  @GetMapping("/latest")
  public List<Collection> getLatestCollections() {
    return collectionService.getLatestCollections();
  }

  @GetMapping("/largest")
  public List<Categories> getLargestCollections() {
    return collectionService.getLargestCollections();
  }

  @GetMapping("/{username}")
  public Optional<Collection> getCollectionByUsername(@PathVariable String username) {
    return collectionService.getCollectionByUsername(username);
  }

  @PostMapping(value = "/")
  public String createCollection(@RequestParam(value = "file", required = false) MultipartFile file,
      @RequestPart Collection collection) {
    Collection saved = collectionService.createCollection(collection);

    if(file != null) {
      FileUploader uploader = new FileUploader();
      String id = String.valueOf(saved.getId());
      return uploader.uploadFile(file, id);
    }else {
      return "Collection saved";
    }
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

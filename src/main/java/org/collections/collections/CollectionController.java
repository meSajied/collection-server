package org.collections.collections;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.collections.utils.FileUploader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

  @GetMapping("/id/{id}")
  public Optional<Collection> getCollectionById(@PathVariable int id) {
    return collectionService.findById(id);
  }

  @GetMapping("/user/{username}")
  public List<Collection> getCollectionByUsername(@PathVariable String username) {
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

  @GetMapping("/uploads/{filename}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    try {
      Path file = Paths.get("uploads/" + filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
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

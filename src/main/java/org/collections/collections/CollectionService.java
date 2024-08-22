package org.collections.collections;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CollectionService {
  private final CollectionRepository collectionRepository;
  private final CategoryRepository categoryRepository;

  public CollectionService(CollectionRepository collectionRepository, CategoryRepository categoryRepository) {
    this.collectionRepository = collectionRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Collection> findAll() {
    return (List<Collection>) collectionRepository.findAll();
  }

  public Optional<Collection> findById(int id) {
    return collectionRepository.findById(id);
  }

  List<Collection> getLatestCollections() {
    Pageable top = (Pageable) PageRequest.of(0,10);
    return collectionRepository.findLatestCollections(top);
  }

  List<Categories> getLargestCollections() {
    Pageable top = (Pageable) PageRequest.of(0, 3);
    return categoryRepository.findLargestCategories(top);
  }

  List<Collection> getCollectionByUsername(String username) {
    return collectionRepository.findByUsername(username);
  }

  Collection createCollection(Collection collection) {
    return collectionRepository.save(collection);
  }

  Optional<Collection> updateCollection(Collection collection) {
    return collectionRepository.findById(collection.getId()).map(existingData -> {
      return updateData(existingData, collection);
    });
  }

  private Collection updateData(Collection existingData, Collection newData) {
    existingData.setName(newData.getName());
    
    return collectionRepository.save(existingData);
  }

  void deleteCollectionBy(String id) {
    collectionRepository.deleteById(id);
  }
}

package org.collections.collections;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
class CollectionService {
  private final CollectionRepository collectionRepository;

  CollectionService(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  List<Collection> getLatestCollections() {
    Pageable top = (Pageable) PageRequest.of(0,10);
    return collectionRepository.findLatestCollections(top);
  }

  List<Collection> getLargestCollections() {
    Pageable top = (Pageable) PageRequest.of(0, 3);
    return collectionRepository.findLargestCollections(top);
  }

  Optional<Collection> getCollectionByUsername(String username) {
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

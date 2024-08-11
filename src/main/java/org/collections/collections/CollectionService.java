package org.collections.collections;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CollectionService {
  @Autowired
  CollectionRepository collectionRepository;

  List<Collection> getLatestCollections() {
    return null;
  }

  List<Collection> getLargestCollections() {
    return null;
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

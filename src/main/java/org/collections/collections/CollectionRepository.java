package org.collections.collections;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
interface CollectionRepository extends CrudRepository<Collection, String> {
  List<Collection> findByUsername(String username);
  Optional<Collection> findById(int id);

  @Query("SELECT c FROM Collection c ORDER BY c.createdAt DESC")
  List<Collection> findLatestCollections(Pageable pageable);
}
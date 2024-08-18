package org.collections.collections;

import org.collections.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
interface CollectionRepository extends CrudRepository<Collection, String> {
  Optional<Collection> findByUsername(String username);
  Optional<Collection> findById(int id);

  @Query("SELECT c FROM Collection c ORDER BY c.createdAt DESC")
  List<Collection> findLatestCollections(Pageable pageable);

  @Query("SELECT c FROM Collection c ORDER BY SIZE(c.categories) DESC")
  List<Collection> findLargestCollections(Pageable pageable);
}
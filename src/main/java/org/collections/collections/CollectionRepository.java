package org.collections.collections;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CollectionRepository extends CrudRepository<Collection, String> {
  Optional<Collection> findByUsername(String username);
}
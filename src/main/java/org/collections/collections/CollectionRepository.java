package org.collections.collections;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CollectionRepository extends CrudRepository<Collection, String> {
  
}
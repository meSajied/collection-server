package org.collections.collections;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
interface CategoryRepository extends CrudRepository<Categories, Integer> {

  @Query("SELECT c FROM Categories c ORDER BY size(c.collection) DESC")
  List<Categories> findLargestCategories(Pageable pageable);
}

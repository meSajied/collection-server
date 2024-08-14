package org.collections.collections;

import org.springframework.data.repository.CrudRepository;

interface CommentRepository extends CrudRepository<Comments, String> {
}

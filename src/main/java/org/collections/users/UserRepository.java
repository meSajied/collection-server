package org.collections.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, String>{
  Optional<User> findByUsername(String username);
  Optional<User> deleteByUsername(String username);
}

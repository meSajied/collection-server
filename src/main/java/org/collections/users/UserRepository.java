package org.collections.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, String>{
  Optional<User> findByUsername(String username);
  Optional<User> findByRole(Role role);
  Optional<User> deleteByUsername(String username);
  int countByRole(Role role);

}

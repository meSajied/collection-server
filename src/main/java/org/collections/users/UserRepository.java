package org.collections.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<AppUser, String>{
  Optional<AppUser> findByUsername(String username);
  Optional<AppUser> findByRole(Role role);
  Optional<AppUser> deleteByUsername(String username);
  int countByRole(Role role);

}

package org.collections.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
class UserService {
  private final UserRepository userRepository;

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  Optional<User> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  User createUser(User user) {
    return userRepository.save(user);
  }

  Optional<User> updateCollection(User user) {
    return userRepository.findById(user.getUsername()).map(existingData -> {
      return updateData(existingData, user);
    });
  }

  private User updateData(User existingData, User newData) {
    existingData.setName(newData.getName());

    return userRepository.save(existingData);
  }

  Optional<User> deleteUserByUsername(String username) {
    return userRepository.deleteByUsername(username);
  }
}

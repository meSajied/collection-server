package org.collections.users;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> getUserByRole(Role role) {
    return userRepository.findByRole(role);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public Optional<User> updateUser(User user) {
    return userRepository.findById(user.getUsername()).map(existingData -> {
      return updateData(existingData, user);
    });
  }

  private User updateData(User existingData, User newData) {
    if(existingData.getName() != null) {
      existingData.setName(newData.getName());
    }

    if(existingData.getPassword() != null) {
      existingData.setPassword(newData.getPassword());
    }

    if(existingData.getRole() != null && countByRole(Role.ADMIN) >= 1) {
      existingData.setRole(newData.getRole());
    }

    return userRepository.save(existingData);
  }

  public Optional<User> deleteUserByUsername(String username) {
    return userRepository.deleteByUsername(username);
  }

  public int countByRole(Role role) {
    return userRepository.countByRole(role);
  }
}

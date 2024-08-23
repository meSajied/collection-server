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

  public Optional<AppUser> getUser(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<AppUser> getUserByRole(Role role) {
    return userRepository.findByRole(role);
  }

  public AppUser createUser(AppUser user) {
    return userRepository.save(user);
  }

  public Optional<AppUser> updateUser(AppUser user) {
    return userRepository.findById(user.getUsername()).map(existingData -> {
      return updateData(existingData, user);
    });
  }

  private AppUser updateData(AppUser existingData, AppUser newData) {
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

  public Optional<AppUser> deleteUserByUsername(String username) {
    return userRepository.deleteByUsername(username);
  }

  public int countByRole(Role role) {
    return userRepository.countByRole(role);
  }
}

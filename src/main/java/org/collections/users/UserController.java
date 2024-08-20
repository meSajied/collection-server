package org.collections.users;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  public Optional<User> getUser(@PathVariable String username) {
    return userService.getUser(username);
  }

  @PostMapping("/")
  public User addUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PatchMapping("/")
  public Optional<User> updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @DeleteMapping("/{username}")
  public Optional<User> deleteUser(@PathVariable String username) {
    return userService.deleteUserByUsername(username);
  }
}
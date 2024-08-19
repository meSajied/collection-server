package org.collections.users;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
class UserController {
  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  Optional<User> getUser(@PathVariable String username) {
    return userService.getUser(username);
  }

  @PostMapping("/")
  User addUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PatchMapping("/")
  Optional<User> updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @DeleteMapping("/{username}")
  Optional<User> deleteUser(@PathVariable String username) {
    return userService.deleteUserByUsername(username);
  }
}
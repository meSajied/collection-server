package org.collections.admin;

import org.collections.collections.Collection;
import org.collections.collections.CollectionService;
import org.collections.users.Role;
import org.collections.users.User;
import org.collections.users.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
class AdminController {
  private final CollectionService collectionService;
  private final UserService userService;

  AdminController(CollectionService collectionService,
      UserService userService) {
    this.collectionService = collectionService;
    this.userService = userService;
  }

  @GetMapping("/list")
  Optional<User> getAllAdmin() {
    return userService.getUserByRole(Role.valueOf("ADMIN"));
  }

  @GetMapping("/collections")
  List<Collection> getAllCollection() {
    return collectionService.findAll();
  }
  @PatchMapping("/")
  String editAdmin(@RequestBody User user) {
    Role role = user.getRole();

    if(role.equals(Role.USER) &&
        userService.countByRole(Role.ADMIN) >= 1) {
      userService.updateUser(user);
      return "done";
    }else {
      return "could not update";
    }
  }
}

package org.collections.users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  @Lazy
  private AuthenticationManager authenticationManager;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    try {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          authRequest.getUsername(), authRequest.getPassword()
      );

      Authentication result = authenticationManager.authenticate(authentication);

      return ResponseEntity.ok(new AuthResponse(result));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Login failed: " + e.getMessage()));
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response, authentication);
    }
    return ResponseEntity.ok("User logged out successfully.");
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
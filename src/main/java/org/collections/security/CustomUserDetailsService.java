package org.collections.security;

import org.collections.users.AppUser;
import org.collections.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserService userService;
  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Supplier<UsernameNotFoundException> supplier = () ->
        new UsernameNotFoundException("username not found");

    AppUser u = userService.getUser(username).orElseThrow(supplier);

    return new CustomUserDetails(u);
  }
}

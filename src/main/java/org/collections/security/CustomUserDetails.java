package org.collections.security;

import org.collections.users.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
  private final AppUser user;

  public CustomUserDetails(AppUser user) {
    this.user = user;
  }

  public AppUser getUser() {
    return user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority roleAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

    return List.of(roleAuthority);
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}

package org.collections.users;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class AppUser {
  @Id
  private String username;
  private String name;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "user_id"))
  private Set<Permissions> permissions;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Set<Permissions> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permissions> permissions) {
    this.permissions = permissions;
  }
}

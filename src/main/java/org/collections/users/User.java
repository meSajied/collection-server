package org.collections.users;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {
  @Id
  private String username;

  private String name;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "username"))
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

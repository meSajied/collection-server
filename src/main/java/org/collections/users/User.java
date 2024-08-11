package org.collections.users;

import jakarta.persistence.*;
import java.util.Set;

import org.collections.collections.Collection;

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

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Collection> collections;

  String getUsername() {
    return username;
  }

  void setUsername(String username) {
    this.username = username;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }
  Role getRole() {
    return role;
  }

  void setRole(Role role) {
    this.role = role;
  }

  Set<Permissions> getPermissions() {
    return permissions;
  }

  void setPermissions(Set<Permissions> permissions) {
    this.permissions = permissions;
  }

  public Set<Collection> getCollections() {
    return collections;
  }

  void setCollections(Set<Collection> collections) {
    this.collections = collections;
  }
}

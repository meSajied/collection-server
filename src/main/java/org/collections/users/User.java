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

  // Constructors


  // Getters and Setters
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
}

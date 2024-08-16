package org.collections.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import org.collections.collections.Collection;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String username;
  private String name;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "permissions", joinColumns = @JoinColumn(name = "user_id"))
  private Set<Permissions> permissions;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Collection> collections;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

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

  public List<Collection> getCollections() {
    return collections;
  }

  public void setCollections(List<Collection> collections) {
    this.collections = collections;
  }
}

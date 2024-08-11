package org.collections.collections;

import jakarta.persistence.*;
import org.collections.users.User;

@Entity
public class Collection {
  @Id
  private String id;

  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  String getId() {
    return id;
  }

  void setId(String id) {
    this.id = id;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  String getDescription() {
    return description;
  }

  void setDescription(String description) {
    this.description = description;
  }

  User getUser() {
    return user;
  }

  void setUser(User user) {
    this.user = user;
  }
}

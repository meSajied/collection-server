package org.collections.collections;

import jakarta.persistence.*;
import org.collections.users.User;

import java.util.List;

@Entity
public class Collection {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name="username")
  private User user;

  @ManyToMany(cascade = CascadeType.ALL)
  List<Categories> categories;

  @OneToMany(mappedBy = "collection")
  List<Comments> comments;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Categories> getCategories() {
    return categories;
  }

  public void setCategories(List<Categories> categories) {
    this.categories = categories;
  }
}

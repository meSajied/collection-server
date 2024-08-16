package org.collections.collections;

import jakarta.persistence.*;
import org.collections.users.User;

import java.util.List;

@Entity
public class Collection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="user_id")
  private User user;

  @ManyToMany(mappedBy = "collection", cascade = CascadeType.ALL)
  List<Categories> categories;

  @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<Comments> comments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public List<Comments> getComments() {
    return comments;
  }

  public void setComments(List<Comments> comments) {
    this.comments = comments;
  }
}

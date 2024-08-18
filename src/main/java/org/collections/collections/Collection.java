package org.collections.collections;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Collection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String description;
  private String username;
  @ManyToMany(mappedBy = "collection", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  List<Categories> categories;

  @OneToMany(mappedBy = "collection", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  List<Comments> comments;

  @CreationTimestamp
  private LocalDateTime createdAt;

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}

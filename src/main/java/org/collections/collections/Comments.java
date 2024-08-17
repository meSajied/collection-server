package org.collections.collections;

import jakarta.persistence.*;

@Entity
class Comments {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private Collection collection;
  private String comment;
  private String username;

  int getId() {
    return id;
  }

  void setId(int id) {
    this.id = id;
  }

  Collection getCollection() {
    return collection;
  }

  void setCollection(Collection collection) {
    this.collection = collection;
  }

  String getComment() {
    return comment;
  }

  void setComment(String comment) {
    this.comment = comment;
  }

  String getUsername() {
    return username;
  }
  void setUsername(String username) {
    this.username = username;
  }
}

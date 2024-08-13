package org.collections.collections;

import jakarta.persistence.*;

@Entity
class Comments {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Collection collection;
  private String comment;

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
}

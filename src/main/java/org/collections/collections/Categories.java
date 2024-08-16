package org.collections.collections;

import jakarta.persistence.*;

import java.util.List;

@Entity
class Categories {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  List<Collection> collection;

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  public List<Collection> getCollection() {
    return collection;
  }

  public void setCollection(List<Collection> collection) {
    this.collection = collection;
  }
}

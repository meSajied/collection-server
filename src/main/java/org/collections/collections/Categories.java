package org.collections.collections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
class Categories {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;

  @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
  @JsonIgnore
  List<Collection> collection;

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

  public List<Collection> getCollection() {
    return collection;
  }

  public void setCollection(List<Collection> collection) {
    this.collection = collection;
  }
}

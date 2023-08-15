package com.betrybe.alexandria.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Classe que representa uma editora.
 */
@Entity
@Table(name = "publishers")
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;

  @OneToMany(mappedBy = "publisher")
  @JsonIgnore
  private List<Book> books;

  public Publisher() {
  }

  /** Construtor da classe.
   *
   * @param id Identificador da editora.
   * @param name Nome da editora.
   * @param address endereço da editora.
   */
  public Publisher(Long id, String name, String address, List<Book> books) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.books = books;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}

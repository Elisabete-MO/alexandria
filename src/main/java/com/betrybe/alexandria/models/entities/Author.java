package com.betrybe.alexandria.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Classe que representa um autor.
 */
@Entity
@Table(name = "authors")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String nationality;

  @ManyToMany(mappedBy = "authors")
  @JsonIgnore
  private List<Book> books;

  public Author() {
  }

  /** Construtor da classe.
   *
   * @param id Identificador do autor.
   * @param name Nome do autor.
   * @param nationality Nacionalidade do autor.
   * @param books Lista de livros do autor.
   */
  public Author(Long id, String name, String nationality, List<Book> books) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
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

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}

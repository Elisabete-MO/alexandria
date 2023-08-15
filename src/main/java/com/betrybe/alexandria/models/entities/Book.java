package com.betrybe.alexandria.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Classe que representa um livro.
 */
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String genre;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
  private BookDetail details;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  public Book() {}

  public Book(Long id, String title, String genre, BookDetail details, Publisher publisher) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.details = details;
    this.publisher = publisher;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public BookDetail getDetails() {
    return details;
  }

  public void setDetails(BookDetail details) {
    this.details = details;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
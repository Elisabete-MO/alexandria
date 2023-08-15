package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Book;
import java.util.List;

/** Classe que representa o DTO de livro.
 *
 * @param id Identificador do livro.
 * @param title Título do livro.
 * @param genre Gênero do livro.
 */
public record BookDTO(Long id, String title, String genre) {
  public Book toBook() {
    return new Book(id, title, genre, null, null, null);
  }
}

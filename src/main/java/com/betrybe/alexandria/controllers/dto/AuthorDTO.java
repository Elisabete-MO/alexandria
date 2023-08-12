package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Author;

/** Classe que representa o DTO de autor.
 *
 * @param id Identificador do autor.
 * @param name Nome do autor.
 * @param nationality Nacionalidade do autor.
 */
public record AuthorDTO(Long id, String name, String nationality) {

  public Author toAuthor() {
    return new Author(id, name, nationality);
  }
}

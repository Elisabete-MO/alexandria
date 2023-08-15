package com.betrybe.alexandria.controllers.dto;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.Publisher;
import java.util.List;

/** Classe que representa o DTO de editora.
 *
 * @param id Identificador do editora.
 * @param name nome da editora.
 * @param address endereço da editora.
 */
public record PublisherDTO(Long id, String name, String address, List<Book> books) {
  public Publisher toPublisher() {
    return new Publisher(id, name, address, books);
  }
}

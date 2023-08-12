package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.AuthorDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Author;
import com.betrybe.alexandria.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe que representa o controller de autores.
 */
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

  private final AuthorService authorService;

  /**
   * Construtor da classe authorController.
   *
   * @param authorService classe service de author.
   */
  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  /**
   * Método que cria um autor.
   *
   * @param authorDTO DTO de autor.
   * @return Resposta de criação de autor.
   */
  @PostMapping()
  public ResponseEntity<ResponseDTO<Author>> createAuthor(@RequestBody AuthorDTO authorDTO) {
    Author newAuthor = authorService.insertAuthor(authorDTO.toAuthor());
    ResponseDTO<Author> responseDTO = new ResponseDTO<>(
        "Autor criado com sucesso!", newAuthor);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /**
   * Método que atualiza um autor.
   *
   * @param authorId  Identificador do autor.
   * @param authorDTO DTO de autor.
   * @return Resposta de atualização de autor.
   */
  @PutMapping("/{authorId}")
  public ResponseEntity<ResponseDTO<Author>> updateAuthor(
      @PathVariable Long authorId, @RequestBody AuthorDTO authorDTO) {
    Optional<Author> optionalAuthor = authorService
        .updateAuthor(authorId, authorDTO.toAuthor());

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>(
        "Autor atualizado com sucesso!", optionalAuthor.get());
    return ResponseEntity.ok(responseDTO);
  }

  /**
   * Método que remove um autor.
   *
   * @param authorId Identificador do autor.
   * @return Resposta de remoção de autor.
   */
  @DeleteMapping("/{authorId}")
  public ResponseEntity<ResponseDTO<Author>> removeAuthorById(@PathVariable Long authorId) {
    Optional<Author> optionalAuthor = authorService.removeAuthorById(authorId);

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>(
        "Autor removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  /**
   * Método que busca um autor.
   *
   * @param authorId Identificador do autor.
   * @return Resposta de busca de autor.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Author>> getAuthorById(@PathVariable Long authorId) {
    Optional<Author> optionalAuthor = authorService.getAuthorById(authorId);

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>(
        "Autor encontrado com sucesso!", optionalAuthor.get());
    return ResponseEntity.ok(responseDTO);
  }

  /**
   * Método que busca todos os autores.
   *
   * @return Resposta de busca de todos os autores.
   */
  @GetMapping()
  public List<AuthorDTO> getAllAuthors() {
    List<Author> allAuthors = authorService.getAllAuthors();
    return allAuthors.stream()
        .map((author) -> new AuthorDTO(
            author.getId(), author.getName(), author.getNationality()))
        .collect(Collectors.toList());
  }
}

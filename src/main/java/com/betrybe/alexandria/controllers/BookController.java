package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.BookDTO;
import com.betrybe.alexandria.controllers.dto.BookDetailDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetail;
import com.betrybe.alexandria.services.BookService;
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
 * Classe que representa o controller de livros.
 */
@RestController
@RequestMapping(value = "/books")
public class BookController {

  private final BookService bookService;

  /** Construtor da classe bookController.
   *
   * @param bookService classe service de book.
   */
  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /** Método que cria um livro.
   *
   * @param bookDTO DTO de livro.
   * @return Resposta de criação de livro.
   */
  @PostMapping()
  public ResponseEntity<ResponseDTO<Book>> createBook(@RequestBody BookDTO bookDTO) {
    Book newBook = bookService.insertBook(bookDTO.toBook());
    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro criado com sucesso!", newBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /** Método que atualiza um livro.
   *
   * @param bookId Identificador do livro.
   * @param bookDTO DTO de livro.
   * @return Resposta de atualização de livro.
   */
  @PutMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> updateBook(
      @PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
    Optional<Book> optionalBook = bookService.updateBook(bookId, bookDTO.toBook());

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Livro atualizado com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  /** Método que remove um livro.
   *
   * @param bookId Identificador do livro.
   * @return Resposta de remoção de livro.
   */
  @DeleteMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> removeBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removeBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  /** Método que busca um livro.
   *
   * @param bookId Identificador do livro.
   * @return Resposta de busca de livro.
   */
  @GetMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> getBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.getBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro encontrado com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
//    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  /** Método que busca todos os livros.
   *
   * @return Resposta de busca de todos os livros.
   */
  @GetMapping()
  public List<BookDTO> getAllBooks() {
    List<Book> allBooks = bookService.getAllBooks();
    return allBooks.stream()
        .map((book) -> new BookDTO(book.getId(), book.getTitle(), book.getGenre()))
        .collect(Collectors.toList());
  }

  @PostMapping("/{bookId}/details/")
  public ResponseEntity<ResponseDTO<Optional<BookDetail>>> createBookDetail(
      @PathVariable Long bookId, @RequestBody BookDetailDTO bookDetailDTO) {
    Optional<BookDetail> newBookDetail = bookService
        .insertBookDetail(bookId, bookDetailDTO.toBookDetail());
    ResponseDTO<Optional<BookDetail>> responseDTO = new ResponseDTO<>(
        "Detalhes do livro criado com sucesso!", newBookDetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> updateBookDetail(
      @PathVariable Long bookId, @RequestBody BookDetailDTO bookDetailDTO) {
    Optional<BookDetail> optionalBookDetail = bookService
        .updateBookDetail(bookId, bookDetailDTO.toBookDetail());

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
        "Detalhes do livro atualizado com sucesso!", optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> removeBookDetailById(@PathVariable Long bookId) {
    Optional<BookDetail> optionalBookDetail = bookService.removeBookDetailById(bookId);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
        "Detalhe do livro removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> getBookDetailById(@PathVariable Long bookId) {
    Optional<BookDetail> optionalBookDetail = bookService.getBookDetailById(bookId);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o detalhe do livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
        "Livro encontrado com sucesso!", optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public ResponseEntity<ResponseDTO<Book>> setPublisherFromBook(
      @PathVariable Long bookId, @PathVariable Long publisherId) {
    Optional<Book> optionalBook = bookService.setPublisher(bookId, publisherId);

    if(optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d ou a editora de ID %d", bookId, publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Editora vinculada ao livro com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/publisher")
  public ResponseEntity<ResponseDTO<Book>> removePublisherFromBook(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removePublisher(bookId);
    if(optionalBook.isEmpty()){
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi possível remover a editora do livro com id %d", bookId),
          null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        String.format("Editora removida do livro de ID %d", bookId),
        optionalBook.get()
    );
    return ResponseEntity.ok().body(responseDTO);
  }

  @PutMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<ResponseDTO<Book>> setAuthorFromBook(
      @PathVariable Long bookId, @PathVariable Long authorId) {
    Optional<Book> optionalBook = bookService.setPublisher(bookId, authorId);

    if(optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrado o livro de ID %d ou a pessoa autora de ID %d",
              bookId, authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        "Autor vinculado ao livro com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<ResponseDTO<Book>> removeAuthorFromBook(
      @PathVariable Long bookId, @PathVariable Long authorId) {
    Optional<Book> optionalBook = bookService.removeAuthor(bookId, authorId);
    if(optionalBook.isEmpty()){
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
          String.format("Não foi possível remover a pessoa autora do livro com id %d", bookId),
          null
      );
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }
    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
        String.format("Autor removido do livro de ID %d", bookId),
        optionalBook.get());
    return ResponseEntity.ok().body(responseDTO);
  }
}

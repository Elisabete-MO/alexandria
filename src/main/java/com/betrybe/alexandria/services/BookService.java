package com.betrybe.alexandria.services;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.repositories.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada service da entidade book
 */
@Service
public class BookService {
  private BookRepository bookRepository;

  /** Construtos da classe BookService
   *
   * @param bookRepository repositorio da entidade book
   */
  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /** Metodo responsavel por enviar os dados de um novo livro ao repositorio.
   *
   * @param book dados para inserção no banco
   * @return retorna uma instância de Book
   */
  public Book insertBook(Book book) {
    return bookRepository.save(book);
  }

  /** Realiza a atualização deste livro no banco de dados.
   *
   * @param id parâmetro recebido: id de um livro
   * @param book uma instância da classe Book
   * @return é retornado um Optional<Book> com a instância armazenada na
   * variável updatedBook, que representa que a operação ocorreu com sucesso.
   */
  public Optional<Book> updateBook(Long id, Book book) {
    Optional<Book> optionalBook = bookRepository.findById(id);

    if (optionalBook.isPresent()) {
      Book bookFromDB = optionalBook.get();
      bookFromDB.setTitle(book.getTitle());
      bookFromDB.setGenre(book.getGenre());

      Book updatedBook = bookRepository.save(bookFromDB);
      return Optional.of(updatedBook);
    }
    return optionalBook;
  }

  /** Realiza a exclusão deste livro no banco de dados.
   *
   * @param id recebe como parâmetro o id de um livro
   * @return só ocorre se o livro nao for encontrado
   */
  public Optional<Book> removeBookById(Long id) {
    Optional<Book> bookOptional = bookRepository.findById(id);

    if(bookOptional.isPresent()) {
      bookRepository.deleteById(id);
    }
    return bookOptional;
  }

  /** Realiza a busca de um livro no banco de dados.
   *
   * @param id recebe como parâmetro o id de um livro
   * @return registro no banco de dados com o id especificado
   */
  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  /** Realiza a busca de todos os livros no banco de dados.
   *
   * @return uma lista com todos os registros
   */
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }
}

package com.betrybe.alexandria.services;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.entities.BookDetail;
import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.models.repositories.BookDetailRepository;
import com.betrybe.alexandria.models.repositories.BookRepository;
import com.betrybe.alexandria.models.repositories.PublisherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada service da entidade book
 */
@Service
public class BookService {
  private final BookRepository bookRepository;
  private final BookDetailRepository bookDetailRepository;
  private final PublisherRepository publisherRepository;

  /** Construtos da classe BookService
   *
   * @param bookRepository repositorio da entidade book
   * @param bookDetailRepository repositorio da entidade bookDetail
   * @param publisherRepository repositorio da entidade publisher
   */
  @Autowired
  public BookService(BookRepository bookRepository,
                     BookDetailRepository bookDetailRepository,
                     PublisherRepository publisherRepository) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherRepository = publisherRepository;
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

  public Optional<BookDetail> insertBookDetail(Long bookId, BookDetail bookDetail) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      bookDetail.setBook(book);
      BookDetail newBookDetail = bookDetailRepository.save(bookDetail);
      return Optional.of(newBookDetail);
    }
    return Optional.empty();
  }

  public Optional<BookDetail> updateBookDetail(Long id, BookDetail bookDetail) {
    Optional<Book> optionalBook = bookRepository.findById(id);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      BookDetail bookDetailFromDB = book.getDetails();
      bookDetailFromDB.setSummary(bookDetail.getSummary());
      bookDetailFromDB.setIsbn(bookDetail.getIsbn());
      bookDetailFromDB.setPageCount(bookDetail.getPageCount());
      bookDetailFromDB.setYear(bookDetail.getYear());

      BookDetail updatedBookDetail = bookDetailRepository.save(bookDetailFromDB);
      return Optional.of(updatedBookDetail);
    }
    return Optional.empty();
  }

  public Optional<BookDetail> removeBookDetailById(Long id) {
    Optional<Book> optionalBook = bookRepository.findById(id);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();

      Optional<BookDetail> optionalBookDetail = bookDetailRepository
          .findById(book.getDetails().getId());
      if (optionalBookDetail.isPresent()) {
        book.setDetails(null); // remove a relação entre book e bookDetail 1:1
        BookDetail bookDetail = optionalBookDetail.get();
        bookDetailRepository.deleteById(bookDetail.getId());
        return Optional.of(bookDetail);
      }
    }
      return Optional.empty();
  }

  public Optional<BookDetail> getBookDetailById(Long id) {
    return bookDetailRepository
        .findById(id);
  }

  public Optional<Book> setPublisher(Long bookId, Long publisherId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(optionalBook.isEmpty()){
      return Optional.empty();
    }

    Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
    if(optionalPublisher.isEmpty()){
      return Optional.empty();
    }
    Book book = optionalBook.get();
    Publisher publisher = optionalPublisher.get();
    book.setPublisher(publisher);
    Book updatedBook = bookRepository.save(book);
    return Optional.of(updatedBook);
  }

  public Optional<Book> removePublisher(Long bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(optionalBook.isEmpty()){
      return Optional.empty();
    }

    Book book = optionalBook.get();
    book.setPublisher(null);

    Book newBook = bookRepository.save(book);
    return Optional.of(newBook);
  }
}

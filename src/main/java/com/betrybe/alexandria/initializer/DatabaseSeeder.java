package com.betrybe.alexandria.initializer;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final BookRepository bookRepository;

  public DatabaseSeeder(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedBooks();
  }

  private void seedBooks() {
    Book book = new Book(1L,"History","The Fall of the Roman Republic", null, null, null);
    bookRepository.save(book);

    book = new Book(2L,"History","The Civil War", null, null, null);
    bookRepository.save(book);

    book = new Book(3L,"History","The World War II", null, null, null);
    bookRepository.save(book);

    book = new Book(4L,"History","The World War I", null, null, null);
    bookRepository.save(book);

    book = new Book(5L,"History","The Cold War", null, null, null);
    bookRepository.save(book);

    book = new Book(6L,"History","The American Revolution", null, null, null);
    bookRepository.save(book);

    book = new Book(7L,"History","The Fall of the Roman Republic", null, null, null);
    bookRepository.save(book);

    book = new Book(8L,"History","The Rise and Fall of Alexandria: Birthplace of the Modern Mind", null, null, null);
    bookRepository.save(book);

    book = new Book(9L,"History","The Alexandrian Library: Glory of the Hellenic World", null, null, null);
    bookRepository.save(book);

    book = new Book(10L,"History","Libraries in the Ancient World", null, null, null);
    bookRepository.save(book);

    book = new Book(11L,"History","Life and Fate of the Ancient Library of Alexandria", null, null, null);
    bookRepository.save(book);

    book = new Book(12L,"History","The Library of Alexandria: Centre of Learning in the Ancient World", null, null, null);
    bookRepository.save(book);

    book = new Book(13L,"History","The Library of Alexandria", null, null, null);
    bookRepository.save(book);

    book = new Book(14L,"History","What are the Seven Wonders of the World?: And 100 Other Great Cultural Lists—Fully Explicated", null, null, null);
    bookRepository.save(book);

    book = new Book(15L,"History","The Vanished Library: A Wonder of the Ancient World", null, null, null);
    bookRepository.save(book);
  }
}
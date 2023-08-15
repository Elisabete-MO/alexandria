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
  }
}
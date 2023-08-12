package com.betrybe.alexandria.models.repositories;

import com.betrybe.alexandria.models.entities.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório de detalhes de livros.
 */
@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
}

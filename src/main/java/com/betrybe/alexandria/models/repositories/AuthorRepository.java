package com.betrybe.alexandria.models.repositories;

import com.betrybe.alexandria.models.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório de autores.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

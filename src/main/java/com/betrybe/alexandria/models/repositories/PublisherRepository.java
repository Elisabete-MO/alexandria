package com.betrybe.alexandria.models.repositories;

import com.betrybe.alexandria.models.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório de editoras.
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

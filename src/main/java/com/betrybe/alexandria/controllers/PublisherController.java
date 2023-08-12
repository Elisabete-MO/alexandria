package com.betrybe.alexandria.controllers;

import com.betrybe.alexandria.controllers.dto.PublisherDTO;
import com.betrybe.alexandria.controllers.dto.ResponseDTO;
import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.services.PublisherService;
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
 * Classe que representa o controller de editoras.
 */
@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

  private final PublisherService publisherService;

  /** Construtor da classe publisherController.
   *
   * @param publisherService classe service de publisher.
   */
  @Autowired
  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  /** Método que cria uma editora.
   *
   * @param publisherDTO DTO de editora.
   * @return Resposta de criação de editora.
   */
  @PostMapping()
  public ResponseEntity<ResponseDTO<Publisher>> createPublisher(
      @RequestBody PublisherDTO publisherDTO) {
    Publisher newPublisher = publisherService.insertPublisher(publisherDTO.toPublisher());
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora criada com sucesso!", newPublisher);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  /** Método que atualiza uma editora.
   *
   * @param publisherId Identificador do editora.
   * @param publisherDTO DTO de editora.
   * @return Resposta de atualização de editora.
   */
  @PutMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> updatePublisher(
      @PathVariable Long publisherId, @RequestBody PublisherDTO publisherDTO) {
    Optional<Publisher> optionalPublisher = publisherService
        .updatePublisher(publisherId, publisherDTO.toPublisher());

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrada a editora de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora atualizada com sucesso!", optionalPublisher.get());
    return ResponseEntity.ok(responseDTO);
  }

  /** Método que remove uma editora.
   *
   * @param publisherId Identificador da editora.
   * @return Resposta de remoção de editora.
   */
  @DeleteMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> removePublisherById(
      @PathVariable Long publisherId) {
    Optional<Publisher> optionalPublisher = publisherService.removePublisherById(publisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrada a editora de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora removida com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  /** Método que busca uma editora.
   *
   * @param publisherId Identificador do editora.
   * @return Resposta de busca de editora.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Publisher>> getPublisherById(
      @PathVariable Long publisherId) {
    Optional<Publisher> optionalPublisher = publisherService.getPublisherById(publisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
          String.format("Não foi encontrada a editora de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
        "Editora encontrada com sucesso!", optionalPublisher.get());
    return ResponseEntity.ok(responseDTO);
  }

  /** Método que busca todas as editoras.
   *
   * @return Resposta de busca de todas as editoras.
   */
  @GetMapping()
  public List<PublisherDTO> getAllPublishers() {
    List<Publisher> allPublishers = publisherService.getAllPublishers();
    return allPublishers.stream()
        .map((publisher) -> new PublisherDTO(publisher
            .getId(), publisher.getName(), publisher.getAddress()))
        .collect(Collectors.toList());
  }
}

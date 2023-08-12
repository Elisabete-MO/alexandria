package com.betrybe.alexandria.services;

import com.betrybe.alexandria.models.entities.Publisher;
import com.betrybe.alexandria.models.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada service da entidade publisher
 */
@Service
public class PublisherService {
  private PublisherRepository publisherRepository;

  /** Construtos da classe PublisherService
   *
   * @param publisherRepository repositorio da entidade publisher
   */
  @Autowired
  public PublisherService(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  /** Metodo responsavel por enviar os dados de uma nova editora ao repositorio.
   *
   * @param publisher dados para inserção no banco
   * @return retorna uma instância de Publisher
   */
  public Publisher insertPublisher(Publisher publisher) {
    return publisherRepository.save(publisher);
  }

  /** Realiza a atualização desta editora no banco de dados.
   *
   * @param id parâmetro recebido: id de uma editora
   * @param publisher uma instância da classe Publisher
   * @return é retornado um Optional<Publisher> com a instância armazenada na
   * variável updatedPublisher, que representa que a operação ocorreu com sucesso.
   */
  public Optional<Publisher> updatePublisher(Long id, Publisher publisher) {
    Optional<Publisher> optionalPublisher = publisherRepository.findById(id);

    if (optionalPublisher.isPresent()) {
      Publisher publisherFromDB = optionalPublisher.get();
      publisherFromDB.setName(publisher.getName());
      publisherFromDB.setAddress(publisher.getAddress());

      Publisher updatedPublisher = publisherRepository.save(publisherFromDB);
      return Optional.of(updatedPublisher);
    }
    return optionalPublisher;
  }

  /** Realiza a exclusão desta editora no banco de dados.
   *
   * @param id recebe como parâmetro o id de uma editora
   * @return só ocorre se o editora nao for encontrada no banco de dados
   */
  public Optional<Publisher> removePublisherById(Long id) {
    Optional<Publisher> publisherOptional = publisherRepository.findById(id);

    if(publisherOptional.isPresent()) {
      publisherRepository.deleteById(id);
    }
    return publisherOptional;
  }

  /** Realiza a busca de uma editora no banco de dados.
   *
   * @param id recebe como parâmetro o id de uma editora
   * @return registro no banco de dados com o id especificado
   */
  public Optional<Publisher> getPublisherById(Long id) {
    return publisherRepository.findById(id);
  }

  /** Realiza a busca de todos as editoras no banco de dados.
   *
   * @return uma lista com todos os registros
   */
  public List<Publisher> getAllPublishers() {
    return publisherRepository.findAll();
  }
}

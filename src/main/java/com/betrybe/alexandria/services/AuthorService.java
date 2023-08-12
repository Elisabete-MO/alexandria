package com.betrybe.alexandria.services;

import com.betrybe.alexandria.models.entities.Author;
import com.betrybe.alexandria.models.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
  private AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  /** Metodo responsavel por enviar os dados de um novo autor ao repositorio.
   *
   * @param author dados para inserção no banco
   * @return retorna uma instância de Author
   */
  public Author insertAuthor(Author author) {
    return authorRepository.save(author);
  }

  /** Realiza a atualização deste autor no banco de dados.
   *
   * @param id parâmetro recebido: id de um autor
   * @param author uma instância da classe Author
   * @return é retornado um Optional<Author> com a instância armazenada na
   * variável updatedAuthor, que representa que a operação ocorreu com sucesso.
   */
  public Optional<Author> updateAuthor(Long id, Author author) {
    Optional<Author> optionalAuthor = authorRepository.findById(id);

    if (optionalAuthor.isPresent()) {
      Author authorFromDB = optionalAuthor.get();
      authorFromDB.setName(author.getName());
      authorFromDB.setNationality(author.getNationality());

      Author updatedAuthor = authorRepository.save(authorFromDB);
      return Optional.of(updatedAuthor);
    }
    return optionalAuthor;
  }

  /** Realiza a exclusão deste autor no banco de dados.
   *
   * @param id recebe como parâmetro o id de um autor
   * @return só ocorre se o autor nao for encontrado
   */
  public Optional<Author> removeAuthorById(Long id) {
    Optional<Author> authorOptional = authorRepository.findById(id);

    if(authorOptional.isPresent()) {
      authorRepository.deleteById(id);
    }
    return authorOptional;
  }

  /** Realiza a busca de um autor no banco de dados.
   *
   * @param id recebe como parâmetro o id de um autor
   * @return registro no banco de dados com o id especificado
   */
  public Optional<Author> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  /** Realiza a busca de todos os autors no banco de dados.
   *
   * @return uma lista com todos os registros
   */
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }
}

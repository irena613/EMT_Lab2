package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab.model.dto.CreateAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {


    List<Author> listAll();

    Optional<Author> findById(Long id);

    Author create(String name, String surname, Long id);

    //vo domain service layer treba da se pravat perku konstruktorite na modelite
    Optional<Author> create(Author author);

    Optional<Author> edit(Long id, Author author);

    void delete(Long id);
}

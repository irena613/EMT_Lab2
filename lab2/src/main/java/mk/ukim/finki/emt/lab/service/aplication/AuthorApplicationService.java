package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.UpdateAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> listAll();

    Optional<DisplayAuthorDto> findById(Long id);
// vo application layer treba da se pravat preku dto, specificno peku cfeateDto za create
    Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> edit(Long id, UpdateAuthorDto updateAuthorDto);

    void delete(Long id);
}

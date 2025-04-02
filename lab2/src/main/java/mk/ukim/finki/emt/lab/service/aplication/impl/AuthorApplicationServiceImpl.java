package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.*;
import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> listAll() {
        return DisplayAuthorDto.from(authorService.listAll());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> create(CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.country());
        if (country.isPresent()) {
            return authorService.create(createAuthorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDto> edit(Long id, UpdateAuthorDto updateAuthorDto) {
        Optional<Country> country = countryService.findById(updateAuthorDto.country());
        return authorService.edit(id,
                        updateAuthorDto.toAuthor(country.orElse(null)))
                .map(DisplayAuthorDto::from);

    }


    @Override
    public void delete(Long id) {
        authorService.delete(id);
    }
}

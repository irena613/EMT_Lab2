package mk.ukim.finki.emt.lab.service.aplication;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> listAll();

    Optional<DisplayCountryDto> findById(Long id);

    // vo application layer treba da se pravat preku dto, specificno peku cfeateDto za create
    Optional<DisplayCountryDto> create(CreateCountryDto createCountry);

    Optional<DisplayCountryDto> edit(Long id, UpdateCountryDto updateCountryDto);

    void delete(Long id);

}

package mk.ukim.finki.emt.lab.service.aplication.impl;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.Country;
import mk.ukim.finki.emt.lab.model.dto.CreateCountryDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayCountryDto;
import mk.ukim.finki.emt.lab.model.dto.UpdateCountryDto;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> listAll() {
        return DisplayCountryDto.from(countryService.listAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> create(CreateCountryDto createCountry) {
        return countryService.create(createCountry.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> edit(Long id, UpdateCountryDto updateCountryDto) {
        return countryService.edit(id, updateCountryDto.toCountry())
                .map(DisplayCountryDto::from);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }
}

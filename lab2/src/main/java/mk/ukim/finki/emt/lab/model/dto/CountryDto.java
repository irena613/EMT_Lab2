package mk.ukim.finki.emt.lab.model.dto;

import lombok.Data;

@Data
public class CountryDto {

    private String name;

    private String continent;

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}

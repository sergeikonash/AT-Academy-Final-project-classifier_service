package app.sergeikonash.classifier_service.controllers;

import app.sergeikonash.classifier_service.dao.entity.Country;
import app.sergeikonash.classifier_service.dto.CountryCreateDto;
import app.sergeikonash.classifier_service.dto.CountryReadDto;
import app.sergeikonash.classifier_service.dto.PageDto;
import app.sergeikonash.classifier_service.service.api.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    private final IService<CountryCreateDto, Country, CountryReadDto> countryService;

    public CountryController(IService<CountryCreateDto, Country, CountryReadDto> countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<CountryCreateDto> create(@RequestBody CountryCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(dto));
    }

    @GetMapping
    public ResponseEntity<PageDto> getAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return ResponseEntity.ok(countryService.getAll(page, size));
    }
}

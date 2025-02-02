package kz.bitlab.JPASpringPro.controllers;

import kz.bitlab.JPASpringPro.model.Country;
import kz.bitlab.JPASpringPro.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class CountryController {

    private final CountryRepository countryRepository;

    @GetMapping(value = "/add-country")
    public String addCountryPage(){
        return "add-country";
    }

    @PostMapping(value = "/add-country")
    public String addCountry(@RequestParam(name = "country_name") String name,
                             @RequestParam(name = "country_code") String code) {

        Country country = new Country();
        country.setName(name);
        country.setCode(code);
        countryRepository.save(country);

        return "redirect:/foods";
    }
}

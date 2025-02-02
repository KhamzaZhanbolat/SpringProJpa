package kz.bitlab.JPASpringPro.controllers;

import kz.bitlab.JPASpringPro.model.Country;
import kz.bitlab.JPASpringPro.repositories.CountryRepository;
import kz.bitlab.JPASpringPro.repositories.FoodRepository;
import kz.bitlab.JPASpringPro.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FoodRepository foodRepository;
    private final CountryRepository countryRepository;

//  --------ДОБАВЛЕНИЕ ФРУКТ------
    @GetMapping(value = "/add-food")
    public String addFoodPage(Model model){
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "add-food";
    }
    @PostMapping(value = "add-food")
    public String addFood(@RequestParam(name = "food_name")String name,
                          @RequestParam(name = "food_calories")int calories,
                          @RequestParam(name = "food_amounts")int amounts,
                          @RequestParam(name = "food_price")int price,
                          @RequestParam(name = "country_id")Long countryId){

        Country country = countryRepository.findById(countryId).orElse(null);
        Food food = Food
                .builder()
                .name(name)
                .calories(calories)
                .amounts(amounts)
                .price(price)
                .country(country)
                .build();
        foodRepository.save(food);

        return "redirect:/foods";
    }
    //  ------ГЛАВНАЯ СТРАНИЦА-----
    @GetMapping(value = "/foods")
    public String foodPage(Model model){
        List<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "foods";
    }

    @GetMapping("/food")
    public String foodDetails(@RequestParam Long id, Model model) {
        Food food = foodRepository.findById(id).orElse(null);
        model.addAttribute("food", food);
        return "food-details";
    }

    @GetMapping(value = "/404")
    public String error(){
        return "404";
    }
    @GetMapping(value = "/update-food")
    public String editFoodPage(Model model,@RequestParam(name = "id")Long id){
        Food food = foodRepository.findById(id).orElse(null);

        if (food!=null){
            model.addAttribute("food",food);

            List<Country> countries = countryRepository.findAll();
            model.addAttribute("countries", countries);

            return "edit-food";
        }else {
            return "redirect:/404";
        }
    }

    @PostMapping(value = "/update-food")
    public String updateFood(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "food_name") String name,
                            @RequestParam(name = "food_calories") int calories,
                            @RequestParam(name = "food_amounts") int amounts,
                            @RequestParam(name = "food_price") int price,
                             @RequestParam(name = "country_id")Long countryId){

        Food food = foodRepository.findById(id).orElse(null);
        Country country = countryRepository.findById(countryId).orElse(null);
        food = Food
                .builder()
                .id(id)
                .name(name)
                .calories(calories)
                .amounts(amounts)
                .price(price)
                .country(country)
                .build();
        foodRepository.save(food);

        return "redirect:/foods";
    }

    @PostMapping(value = "/delete-food")
    public String deleteFood(@RequestParam(name = "id") Long id) {
        foodRepository.deleteById(id); // Нет проверки существования записи!
        return "redirect:/foods";

    }
}

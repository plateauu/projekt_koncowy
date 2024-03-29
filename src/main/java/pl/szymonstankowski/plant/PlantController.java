package pl.szymonstankowski.plant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Plant> plants = plantService.getPlants();
        List<Plant> nonEditablePlantList = new ArrayList<>();
        for (Plant plant : plants) {
            if (!plant.isEditable() && plant.isActive()) {
                nonEditablePlantList.add(plant);
            }
        }
        model.addAttribute("listOfPlants", nonEditablePlantList);
        return "home-page";
    }

}

package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Order;
import sia.tacocloud.domain.Taco;
import sia.tacocloud.model.TacoModel;
import sia.tacocloud.repository.IngredientRepository;
import sia.tacocloud.repository.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository,
                                TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        ingredients.stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getType().toString().toLowerCase()))
                .forEach(model::addAttribute);
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public TacoModel taco() {
        return new TacoModel();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        return "tacoDesign";
    }

    @PostMapping
    public String processDesign(@Valid TacoModel taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "tacoDesign";
        }
        log.info("Processing design: " + taco);
        Taco saved = tacoRepository.save(mapToTaco(taco));
        order.addTaco(saved);
        return "redirect:/orders/current";
    }

    private Taco mapToTaco(TacoModel tacoModel) {
        List<Ingredient> ingredients = new ArrayList<>();
        tacoModel.getIngredients()
                .forEach(
                        ingredient -> {
                            Ingredient ingredientTO = ingredientRepository.findById(ingredient)
                                    .orElseThrow(() -> new IllegalArgumentException("Ingredient not valid"));
                            ingredients.add(ingredientTO);
                        }
                );
        return new Taco(tacoModel.getName(), ingredients);
    }
}

package sia.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.domain.Ingredient;
import sia.tacocloud.domain.Ingredient.Type;
import sia.tacocloud.domain.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                Ingredient.builder().id("FLTO").name("Flour Tortilla").type(Type.WRAP).build(),
                Ingredient.builder().id("COTO").name("Corn Tortilla").type(Type.WRAP).build(),
                Ingredient.builder().id("GRBF").name("Ground Beef").type(Type.PROTEIN).build(),
                Ingredient.builder().id("CARN").name("Carnitas").type(Type.PROTEIN).build(),
                Ingredient.builder().id("TMTO").name("Diced Tomatoes").type(Type.VEGGIES).build(),
                Ingredient.builder().id("LETC").name("Lettuce").type(Type.VEGGIES).build(),
                Ingredient.builder().id("CHED").name("Cheddar").type(Type.CHEESE).build(),
                Ingredient.builder().id("JACK").name("Monterrey Jack").type(Type.CHEESE).build(),
                Ingredient.builder().id("SLSA").name("Salsa").type(Type.SAUCE).build(),
                Ingredient.builder().id("SRCR").name("Sour Cream").type(Type.SAUCE).build()
        );
        ingredients.stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getType().toString().toLowerCase()))
                .forEach(model::addAttribute);
        model.addAttribute("design", new Taco());
        return "tacoDesign";
    }

    @PostMapping
    public String processDesign(Taco taco) {
// Save the taco design...
// We'll do this in chapter 3
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }
}

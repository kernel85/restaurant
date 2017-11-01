package restaurant.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Restaurant {

    @JsonCreator public Restaurant(@JsonProperty("chefs") int chefs, @JsonProperty("menu") List<Dish> dishes) {
        this.chefs  = chefs;
        this.dishes = requireNonNull(dishes);
    }

    @JsonProperty("chefs") private int chefs;
    @JsonProperty("menu")  private List<Dish> dishes = new LinkedList<>();

    public int        getChefs()  { return chefs; }
    public List<Dish> getDishes() { return dishes; }

}

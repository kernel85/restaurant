package restaurant.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

public class Dish {

    @JsonCreator public Dish(@JsonProperty("name") String name, @JsonProperty("cooking_time") int cookingTime) {
        this.name        = requireNonNull(name);
        this.cookingTime = cookingTime;
    }

    @JsonProperty("name")         private String name;
    @JsonProperty("cooking_time") private int    cookingTime;

    public String getName()        { return name; }
    public int    getCookingTime() {  return cookingTime; }

}

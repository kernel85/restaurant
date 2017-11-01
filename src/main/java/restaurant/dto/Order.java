package restaurant.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Order {

    @JsonCreator public Order(@JsonProperty("table") int table, @JsonProperty("food") List<String> orderedDishes) {
        this.table         = table;
        this.orderedDishes = requireNonNull(orderedDishes);
    }

    @JsonProperty("table") private final int          table;
    @JsonProperty("food")  private final List<String> orderedDishes;

    public int          getTable()         { return table; }
    public List<String> getOrderedDishes() {  return orderedDishes; }

    @Override public String toString() {
        return "table: " + table + " => food: " + orderedDishes.stream().collect(Collectors.joining(", "));
    }

}

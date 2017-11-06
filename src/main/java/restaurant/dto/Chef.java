package restaurant.dto;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Chef {

    public static final Comparator<Chef> SORT_BY_COOKING_TIME = Comparator.comparing(Chef::getTotalCookingTime)
                                                                          .thenComparing(c -> c.getCookedDishes().size());

    public Chef(int chefNum) {
        name         = "chef_" + chefNum;
        cookedDishes = new LinkedList<>();
    }

    private final String     name;
    private final List<Dish> cookedDishes;

    public String     getName()             { return name; }
    public List<Dish> getCookedDishes()     { return cookedDishes; }
    public int        getTotalCookingTime() { return cookedDishes.stream().map(Dish::getCookingTime).reduce(0, Integer::sum); }

    public void cook(Dish dish) { cookedDishes.add(dish); }

}

package restaurant;

import org.junit.Test;
import restaurant.dto.Chef;
import restaurant.dto.CookedDish;
import restaurant.dto.Dish;
import restaurant.dto.Order;
import restaurant.service.RestaurantService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class RestaurantRestControllerTest {

    private final static Dish HAMBURGER   = new Dish("hamburger",   300);
    private final static Dish SALAD       = new Dish("salad",       100);
    private final static Dish RATATOUILLE = new Dish("ratatouille", 400);
    private final static Dish TOFU_BURGER = new Dish("tofu_burger", 200);

    @Test public void testOneOrderThreeChefs() throws Exception {
        RestaurantService restaurantService = initRestaurantService(3); // 3 chefs
        restaurantService.acceptOrder(new Order(1, Arrays.asList(HAMBURGER.getName(), SALAD.getName()))); // table 1
        List<CookedDish> expected = Arrays.asList(
            new CookedDish(1, HAMBURGER.getName(), "chef_1", 0, 300),
            new CookedDish(1, SALAD.getName(),     "chef_2", 0, 100)
        );
        assertEquals(expected, restaurantService.getCookedDishes());
    }

    @Test public void testTwoOrdersOneChef() throws Exception {
        RestaurantService restaurantService = initRestaurantService(1); // 1 chef
        restaurantService.acceptOrder(new Order(1, Arrays.asList(HAMBURGER.getName(), SALAD.getName()))); // table 1
        restaurantService.acceptOrder(new Order(2, Arrays.asList(SALAD.getName(),     SALAD.getName()))); // table 2

        List<CookedDish> expected = Arrays.asList(
            new CookedDish(1, HAMBURGER.getName(), "chef_1",   0, 300),
            new CookedDish(1, SALAD.getName(),     "chef_1", 300, 400),
            new CookedDish(2, SALAD.getName(),     "chef_1", 400, 500),
            new CookedDish(2, SALAD.getName(),     "chef_1", 500, 600)
        );
        
        assertEquals(expected, restaurantService.getCookedDishes());
    }

    @Test public void testTwoOrdersTwoChef() throws Exception {
        RestaurantService restaurantService = initRestaurantService(2); // 2 chef
        restaurantService.acceptOrder(new Order(1, Arrays.asList(HAMBURGER.getName(), SALAD.getName()))); // table 1
        restaurantService.acceptOrder(new Order(2, Arrays.asList(SALAD.getName(),     SALAD.getName()))); // table 2

        List<CookedDish> expected = Arrays.asList(
            new CookedDish(1, HAMBURGER.getName(), "chef_1",   0, 300),
            new CookedDish(1, SALAD.getName(),     "chef_2",   0, 100),
            new CookedDish(2, SALAD.getName(),     "chef_2", 100, 200),
            new CookedDish(2, SALAD.getName(),     "chef_2", 200, 300)
        );
        
        assertEquals(expected, restaurantService.getCookedDishes());
    }

    @Test public void testTwoOrdersThreeChef() throws Exception {
        RestaurantService restaurantService = initRestaurantService(3); // 2 chef
        restaurantService.acceptOrder(new Order(1, Arrays.asList(HAMBURGER.getName(), SALAD.getName()))); // table 1
        restaurantService.acceptOrder(new Order(2, Arrays.asList(SALAD.getName(),     SALAD.getName()))); // table 2

        List<CookedDish> expected = Arrays.asList(
            new CookedDish(1, HAMBURGER.getName(), "chef_1",   0, 300),
            new CookedDish(1, SALAD.getName(),     "chef_2",   0, 100),
            new CookedDish(2, SALAD.getName(),     "chef_3",   0, 100),
            new CookedDish(2, SALAD.getName(),     "chef_2", 100, 200)
        );
        
        assertEquals(expected, restaurantService.getCookedDishes());
    }

    @Test public void testThreeOrdersThreeChef() throws Exception {
        RestaurantService restaurantService = initRestaurantService(3); // 2 chef
        restaurantService.acceptOrder(new Order(1, Arrays.asList(HAMBURGER.getName(),   SALAD.getName())));                        // table 1
        restaurantService.acceptOrder(new Order(2, Arrays.asList(SALAD.getName(),       SALAD.getName())));                        // table 2
        restaurantService.acceptOrder(new Order(3, Arrays.asList(RATATOUILLE.getName(), TOFU_BURGER.getName(), SALAD.getName()))); // table 3

        List<CookedDish> expected = Arrays.asList(
            new CookedDish(1, HAMBURGER.getName(),   "chef_1",   0, 300),
            new CookedDish(1, SALAD.getName(),       "chef_2",   0, 100),
            new CookedDish(2, SALAD.getName(),       "chef_3",   0, 100),
            new CookedDish(2, SALAD.getName(),       "chef_2", 100, 200),
            new CookedDish(3, RATATOUILLE.getName(), "chef_3", 100, 500),
            new CookedDish(3, TOFU_BURGER.getName(), "chef_2", 200, 400),
            new CookedDish(3, SALAD.getName(),       "chef_1", 300, 400)
        );

        assertEquals(expected, restaurantService.getCookedDishes());
    }

    // private utils

    private static final RestaurantService initRestaurantService(int chefs) {
        return new RestaurantService(new RestaurantConfig(
            IntStream.range(1, chefs+1).mapToObj(Chef::new).collect(Collectors.toList()),
            Arrays.asList(HAMBURGER, SALAD, RATATOUILLE, TOFU_BURGER)
        ));
    }

}

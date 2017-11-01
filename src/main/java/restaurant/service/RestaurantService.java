package restaurant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.RestaurantConfig;
import restaurant.dto.Chef;
import restaurant.dto.CookedDish;
import restaurant.dto.Order;

import java.util.LinkedList;
import java.util.List;

@Service
public class RestaurantService {

    private final static Logger LOG = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired public RestaurantService(RestaurantConfig config) {
        this.config = config;
    }

    private final RestaurantConfig config;
    private final List<Order>      incomingOrders = new LinkedList<>();
    private final List<CookedDish> cookedDishes   = new LinkedList<>();

    public boolean acceptOrder(Order order) {
        LOG.info("accepting order: {}", order);
        return incomingOrders.add(order);
    }

    public List<CookedDish> getCookedDishes() {
        // 1. clear cooked dishes
        cookedDishes.clear();
        // 2. reset cooking time for each chef
        config.getAllChefs().stream().forEach(Chef::reset);
        // 3. compute cooked dished
        incomingOrders.forEach(order -> {
            order.getOrderedDishes().stream().map(dishName -> config.getAllDishes().get(dishName)).forEach(dish -> {
                config.getAllChefs().stream().sorted(Chef.SORT_BY_COOKING_TIME).findFirst().ifPresent(chef -> {
                    cookedDishes.add(CookedDish.of(order.getTable(), dish, chef));
                    chef.cook(dish);
                });
            });
        });
        return cookedDishes;
    }

}

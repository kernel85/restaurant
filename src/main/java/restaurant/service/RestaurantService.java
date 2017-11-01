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
    private final List<CookedDish> cookedDishes = new LinkedList<>();

    public boolean acceptOrder(Order order) {
        LOG.info("accepting order: {}", order);

        if (config.getAllChefs().isEmpty()) {
            LOG.error("no chef available");
            return false;
        }

        order.getOrderedDishes().stream().map(dishName -> config.getAllDishes().get(dishName)).forEach(dish -> {
            config.getAllChefs().stream().sorted(Chef.SORT_BY_COOKING_TIME).findFirst().ifPresent(chef -> {
                cookedDishes.add(CookedDish.of(order.getTable(), dish, chef));
                chef.cook(dish);
            });
        });

        return true;

    }

    public List<CookedDish> getCookedDishes() {
        return cookedDishes;
    }

}

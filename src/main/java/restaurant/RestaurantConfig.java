package restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import restaurant.dto.Chef;
import restaurant.dto.Dish;
import restaurant.dto.Restaurant;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

@Configuration
public class RestaurantConfig {

    private final static Logger LOG = LoggerFactory.getLogger(RestaurantConfig.class);

    public RestaurantConfig() {
        this.allChefs  = new LinkedList<>();
        this.allDishes = new LinkedHashMap<>();
    }

    RestaurantConfig(List<Chef> allChefs, List<Dish> allDishes) {
        this.allChefs  = requireNonNull(allChefs);
        this.allDishes = requireNonNull(allDishes).stream().collect(Collectors.toMap(Dish::getName, Function.identity()));
    }

    private final List<Chef>        allChefs;
    private final Map<String, Dish> allDishes;

    public List<Chef>        getAllChefs()  { return allChefs; }
    public Map<String, Dish> getAllDishes() { return allDishes; }

    @PostConstruct public void init() {
        LOG.info("Loading restaurant configuration ...");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Restaurant restaurant = mapper.readValue(new ClassPathResource("restaurant.json").getFile(), Restaurant.class);
            LOG.info("... loaded {} chefs!", restaurant.getChefs());
            LOG.info("... loaded {} dishes!", restaurant.getDishes().size());
            LOG.info("... completed!");
            IntStream.range(1, restaurant.getChefs()+1).mapToObj(Chef::new).forEach(allChefs::add);
            restaurant.getDishes().forEach(dish -> allDishes.put(dish.getName(), dish));
        } catch (IOException e) {
            LOG.error("There was an exception loading restaurant.json", e);
        }
    }

}

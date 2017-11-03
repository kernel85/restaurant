package restaurant.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurant.dto.CookedDish;
import restaurant.dto.Order;
import restaurant.service.RestaurantService;

import java.util.List;

@RestController
public class RestaurantRestController {

    @Autowired RestaurantService restaurantService;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public @ResponseBody Response addOrder(@RequestBody Order order) {
        return restaurantService.acceptOrder(order) ? Response.ok() : Response.ko();
    }

    @RequestMapping(value = "/dishes", method = RequestMethod.GET)
    public @ResponseBody List<CookedDish> dishes() {
        return restaurantService.getCookedDishes();
    }

    protected static class Response {

        public static Response ok() { return new Response("order received"); }
        public static Response ko() { return new Response("order failed"); }

        private Response(String msg) { this.msg = msg; }

        @JsonProperty("info") private final String msg;

        public String getMsg() { return msg; }

    }

}

package com.example.CoffeeShop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.CoffeeShop.Service.CoffeeService;
import com.example.CoffeeShop.entity.Coffee;
import com.example.CoffeeShop.entity.Order;

import java.util.List;

@Controller
@RequestMapping("/")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/contact-us")
    public String contactUs() {
        return "contact";
    }

    @GetMapping("/buycoffee")
    public String getCoffees(Model model) {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        model.addAttribute("coffees", coffees);
        return "buycoffee";
    }

    @PostMapping("/buycoffee/{coffeeId}")
    public ResponseEntity<String> buyCoffee(@PathVariable Long coffeeId) {
        Coffee coffee = coffeeService.getCoffeeById(coffeeId);
        if (coffee != null) {
            Order order = new Order(coffee.getName(), "PENDING");
            coffeeService.saveOrder(order);
            return ResponseEntity.ok("Order placed successfully for " + coffee.getName());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        List<Order> orders = coffeeService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PutMapping("/orders/accept/{orderId}")
    public ResponseEntity<String> acceptOrder(@PathVariable Long orderId) {
        Order order = coffeeService.getOrderById(orderId);
        if (order != null) {
            coffeeService.updateOrderStatus(orderId, "Accepted"); // Call updateOrderStatus with orderId and status
            return ResponseEntity.ok("Order accepted for " + order.getCoffeeName());
        }
        return ResponseEntity.notFound().build();
    }
    
}

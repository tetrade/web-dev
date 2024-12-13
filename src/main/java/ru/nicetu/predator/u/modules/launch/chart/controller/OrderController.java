package ru.nicetu.predator.u.modules.launch.chart.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nicetu.predator.u.modules.launch.chart.entities.Order;
import ru.nicetu.predator.u.modules.launch.chart.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders(Authentication authentication) {
        return ResponseEntity.ok(orderService.getOrders(authentication.getName()));
    }

    @PostMapping("/order")
    public int createOrder(
            @RequestParam(name = "price") long price, @RequestParam(name = "count") int count,
            Authentication authentication
    ) {
        return orderService.createOrder(authentication.getName(), price, count);
    }
}

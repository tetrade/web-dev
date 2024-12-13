package ru.nicetu.predator.u.modules.launch.chart.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ru.nicetu.predator.u.modules.launch.chart.entities.Order;
import ru.nicetu.predator.u.modules.launch.chart.entities.User;
import ru.nicetu.predator.u.modules.launch.chart.repository.OrderRepository;
import ru.nicetu.predator.u.modules.launch.chart.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public int createOrder(String userLogin, long price, int count) {

        User user = userRepository.findUserByLogin(userLogin).orElse(null);

        Order order = orderRepository.save(
                Order.builder()
                        .count(count)
                        .price(price)
                        .date(LocalDate.now())
                        .user(user)
                        .build()
        );
        return order.getId();
    }

    @Transactional
    public List<Order> getOrders(String userLogin) {
        return orderRepository.findAll().stream().filter(order ->
                Objects.equals(order.getUser().getLogin(), userLogin)
        ).collect(Collectors.toList());
    }
}


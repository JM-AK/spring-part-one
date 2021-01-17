package ru.geekbrains.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Order;
import ru.geekbrains.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

}

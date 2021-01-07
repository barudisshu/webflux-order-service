package com.cplier.order.service.impl;

import com.cplier.order.repository.OrderRepository;
import com.cplier.order.domain.Order;
import com.cplier.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;

  @Override
  public Mono<Order> createOrder(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Mono<Order> findById(Long orderId) {
    return orderRepository.findById(orderId);
  }

  @Override
  public Mono<Order> updateOrder(Long orderId, Order order) {
    return orderRepository
        .findById(orderId)
        .flatMap(
            dbOrder -> {
              dbOrder.setName(order.getName());
              dbOrder.setPrice(order.getPrice());
              dbOrder.setTime(order.getTime());
              return orderRepository.save(dbOrder);
            });
  }

  @Override
  public Mono<Order> deleteOrder(Long orderId) {
    return orderRepository
        .findById(orderId)
        .flatMap(
            existingOrder -> orderRepository.delete(existingOrder).then(Mono.just(existingOrder)));
  }
}

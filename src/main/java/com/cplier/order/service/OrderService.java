package com.cplier.order.service;

import com.cplier.order.domain.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

  Mono<Order> createOrder(Order order);

  Flux<Order> getAllOrders();

  Mono<Order> findById(Long orderId);

  Mono<Order> updateOrder(Long orderId, Order order);

  Mono<Order> deleteOrder(Long orderId);
}

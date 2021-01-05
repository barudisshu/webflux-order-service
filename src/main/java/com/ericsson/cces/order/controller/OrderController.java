package com.ericsson.cces.order.controller;

import com.ericsson.cces.order.domain.Order;
import com.ericsson.cces.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

  @Autowired private OrderService orderService;

  @GetMapping(value = {"/", "/index"})
  public Mono<String> index() {
    return Mono.just("hello world");
  }

  @PostMapping("/order")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Order> create(@RequestBody Order order) {
    return orderService.createOrder(order);
  }

  @GetMapping("/order")
  public Flux<Order> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("/order/{orderId}")
  public Mono<ResponseEntity<Order>> getOrderById(@PathVariable Long orderId) {
    Mono<Order> order = orderService.findById(orderId);
    return order.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/order/{orderId}")
  public Mono<ResponseEntity<Void>> deleteOrderById(@PathVariable Long orderId) {
    return orderService
        .deleteOrder(orderId)
        .map(r -> ResponseEntity.ok().<Void>build())
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}

package com.ericsson.cces.order.repository;

import com.ericsson.cces.order.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}

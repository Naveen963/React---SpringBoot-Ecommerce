package com.ecommerce.sb_ecom.repositories;

import com.ecommerce.sb_ecom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

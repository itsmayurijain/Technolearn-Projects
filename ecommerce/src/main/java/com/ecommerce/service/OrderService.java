package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductInterface;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductInterface productRepository;

    public Order placeOrder(List<Cart> cartItems) {

        double total = 0;

        for (Cart item : cartItems) {
            Product product = item.getProduct();

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Stock not available");
            }

            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productRepository.save(product);

            total += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setTotalAmount(total);
        order.setStatus("CREATED");

        return orderRepository.save(order);
    }
}

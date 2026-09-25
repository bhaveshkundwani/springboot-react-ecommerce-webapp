package com.ecom.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dto.OrderItemRequest;
import com.ecom.dto.OrderItemResponse;
import com.ecom.dto.OrderRequest;
import com.ecom.dto.OrderResponse;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.Product;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		
		Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());
        
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));
            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())))
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                itemResponses
        );

        return orderResponse;
	}

	public List<OrderResponse> getAllOrderResponses() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for(OrderItem item : order.getOrderItems()) {
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);
            }

            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

}

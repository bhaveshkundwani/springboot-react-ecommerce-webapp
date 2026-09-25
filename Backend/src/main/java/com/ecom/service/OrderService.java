package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.dto.OrderRequest;
import com.ecom.dto.OrderResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		return null;
	}

	public List<OrderResponse> getAllOrderResponses() {
		return null;
	}

}

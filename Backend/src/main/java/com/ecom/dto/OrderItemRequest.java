package com.ecom.dto;

public record OrderItemRequest(
		int productId,
	    int quantity
) {}

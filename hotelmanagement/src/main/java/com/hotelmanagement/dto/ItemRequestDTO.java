package com.hotelmanagement.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemRequestDTO {
	private String name;
	private BigDecimal price;
	private String description;
	private String category;
	private String image;
	private Long restaurantId;
}
package com.hotelmanagement.dto;

import lombok.Data;

@Data
public class RestaurantRequestDTO {
	private String restaurantName;
	private String restaurantAddress;
	private String restaurantPhone;
}
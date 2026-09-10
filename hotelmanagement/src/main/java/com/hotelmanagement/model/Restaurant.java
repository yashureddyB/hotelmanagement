package com.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "restaurant_name", nullable = false)
	private String restaurantName;

	@Column(name = "restaurant_address", nullable = false)
	private String restaurantAddress;

	@Column(name = "restaurant_phone", nullable = false)
	private String restaurantPhone;
}
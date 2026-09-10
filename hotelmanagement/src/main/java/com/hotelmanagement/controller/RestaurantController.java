package com.hotelmanagement.controller;

import com.hotelmanagement.dto.RestaurantRequestDTO;
import com.hotelmanagement.model.Restaurant;
import com.hotelmanagement.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@PostMapping
	public ResponseEntity<Restaurant> create(@RequestBody RestaurantRequestDTO restaurantRequestDTO) {
		Restaurant restaurant = restaurantService.create(restaurantRequestDTO);
		return ResponseEntity.ok(restaurant);
	}

	@GetMapping
	public ResponseEntity<List<Restaurant>> findAll() {
		List<Restaurant> restaurants = restaurantService.findAll();
		return ResponseEntity.ok(restaurants);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.findById(id);
		return ResponseEntity.ok(restaurant);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateById(@PathVariable Long id, @RequestBody RestaurantRequestDTO restaurantRequestDTO) {
		Restaurant restaurant = restaurantService.updateById(id, restaurantRequestDTO);
		return ResponseEntity.ok(restaurant);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		restaurantService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
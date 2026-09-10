package com.hotelmanagement.service;

import com.hotelmanagement.dto.RestaurantRequestDTO;
import com.hotelmanagement.model.Restaurant;
import com.hotelmanagement.model.repository.RestaurantRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
	private final RestaurantRepo restaurantRepo;

	public RestaurantService(RestaurantRepo restaurantRepo) {
		this.restaurantRepo = restaurantRepo;

	}

	public Restaurant create(RestaurantRequestDTO restaurantRequestDTO){
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
		restaurant.setRestaurantAddress(restaurantRequestDTO.getRestaurantAddress());
		restaurant.setRestaurantPhone(restaurantRequestDTO.getRestaurantPhone());
		return restaurantRepo.save(restaurant);
	}

	public List<Restaurant> findAll() {
		return restaurantRepo.findAll();
	}

	public Restaurant findById(Long id){
		return restaurantRepo.findById(id)
							 .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
	}

	public Restaurant updateById(Long id, RestaurantRequestDTO restaurantRequestDTO){
		Restaurant restaurant = findById(id);
		restaurant.setRestaurantName(restaurantRequestDTO.getRestaurantName());
		restaurant.setRestaurantAddress(restaurantRequestDTO.getRestaurantAddress());
		restaurant.setRestaurantPhone(restaurantRequestDTO.getRestaurantPhone());
		return restaurantRepo.save(restaurant);
	}

	public void deleteById(Long id){
		restaurantRepo.deleteById(id);
	}
}

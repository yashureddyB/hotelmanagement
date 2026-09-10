package com.hotelmanagement.service;

import com.hotelmanagement.dto.ItemRequestDTO;
import com.hotelmanagement.model.Item;
import com.hotelmanagement.model.Restaurant;
import com.hotelmanagement.model.repository.ItemRepo;
import com.hotelmanagement.model.repository.RestaurantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepo itemRepo;
	private final RestaurantRepo restaurantRepo;

	public Item create(ItemRequestDTO request) {
		Restaurant restaurant = restaurantRepo.findById(request.getRestaurantId())
											  .orElseThrow(() -> new RuntimeException("Restaurant not found: " + request.getRestaurantId()));

		Item item = new Item();
		item.setName(request.getName());
		item.setPrice(request.getPrice());
		item.setDescription(request.getDescription());
		item.setCategory(request.getCategory());
		item.setImage(request.getImage());
		item.setRestaurant(restaurant);
		return itemRepo.save(item);
	}

	public List<Item> getAll() {
		return itemRepo.findAll();
	}

	public List<Item> getByRestaurant(Long restaurantId) {
		return itemRepo.findByRestaurantId(restaurantId);
	}

	public Item getById(Long id) {
		return itemRepo.findById(id)
					   .orElseThrow(() -> new RuntimeException("Item not found: " + id));
	}

	public Item update(Long id, ItemRequestDTO request) {
		Item item = getById(id);
		item.setName(request.getName());
		item.setPrice(request.getPrice());
		item.setDescription(request.getDescription());
		item.setCategory(request.getCategory());
		item.setImage(request.getImage());
		return itemRepo.save(item);
	}

	public void delete(Long id) {
		itemRepo.deleteById(id);
	}
}
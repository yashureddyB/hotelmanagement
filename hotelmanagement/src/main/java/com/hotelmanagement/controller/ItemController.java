package com.hotelmanagement.controller;

import com.hotelmanagement.dto.ItemRequestDTO;
import com.hotelmanagement.model.Item;
import com.hotelmanagement.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping
	public ResponseEntity<Item> create(@RequestBody ItemRequestDTO request) {
		Item item = itemService.create(request);
		return ResponseEntity.ok(item);
	}

	@GetMapping
	public ResponseEntity<List<Item>> getAll() {
		List<Item> items = itemService.getAll();
		return ResponseEntity.ok(items);
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Item>> getByRestaurant(@PathVariable Long restaurantId) {
		List<Item> items = itemService.getByRestaurant(restaurantId);
		return ResponseEntity.ok(items);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getById(@PathVariable Long id) {
		Item item = itemService.getById(id);
		return ResponseEntity.ok(item);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody ItemRequestDTO request) {
		Item item = itemService.update(id, request);
		return ResponseEntity.ok(item);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
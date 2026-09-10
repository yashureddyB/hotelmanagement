package com.hotelmanagement.model.repository;

import com.hotelmanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
	List<Item> findByRestaurantId(Long restaurantId);
}

package com.hotelmanagement.model.repository;

import com.hotelmanagement.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

}

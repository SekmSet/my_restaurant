package com.my_restaurant.my_restaurant.repository;

import com.my_restaurant.my_restaurant.entity.Carte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteRepository extends CrudRepository<Carte, Long> { }

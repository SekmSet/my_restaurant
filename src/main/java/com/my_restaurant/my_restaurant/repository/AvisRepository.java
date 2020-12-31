package com.my_restaurant.my_restaurant.repository;

import com.my_restaurant.my_restaurant.entity.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> { }

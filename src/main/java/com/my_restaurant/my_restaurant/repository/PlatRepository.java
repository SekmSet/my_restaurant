package com.my_restaurant.my_restaurant.repository;

import com.my_restaurant.my_restaurant.entity.Plat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatRepository extends CrudRepository<Plat, Long> { }

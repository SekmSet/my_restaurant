package com.my_restaurant.my_restaurant.repository;

import com.my_restaurant.my_restaurant.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> { }

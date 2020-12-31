package com.my_restaurant.my_restaurant.repository;

import com.my_restaurant.my_restaurant.entity.Avis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {

    @Query(value = "select * from avis a order by a.created_at desc limit :limit", nativeQuery = true)
    public List<Avis> findOrderedBySeatNumberLimitedTo(@Param("limit") long limit);
}

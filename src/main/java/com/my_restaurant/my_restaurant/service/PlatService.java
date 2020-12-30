package com.my_restaurant.my_restaurant.service;
import com.my_restaurant.my_restaurant.entity.Plat;
import com.my_restaurant.my_restaurant.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    public List<Plat> findAll(){
        var results = platRepository.findAll();

        var plats = new ArrayList<Plat>();
        results.forEach(plats::add);

        return plats;
    }

    public void save(Plat plat) {
        platRepository.save(plat);
    }

    public Plat findById(long id){
        return platRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Le plat n'a pas été trouvé"));
    }

   public void delete(Plat plat){
       platRepository.delete(plat);
    }
}

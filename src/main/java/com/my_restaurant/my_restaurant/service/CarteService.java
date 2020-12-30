package com.my_restaurant.my_restaurant.service;
import com.my_restaurant.my_restaurant.entity.Carte;
import com.my_restaurant.my_restaurant.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

    public List<Carte> findAll(){
        var results = carteRepository.findAll();

        var cartes = new ArrayList<Carte>();
        results.forEach(cartes::add);

        return cartes;
    }

    public void save(Carte carte) {
        carteRepository.save(carte);
    }

    public Carte findById(long id){
        return carteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Le plat n'a pas été trouvé"));
    }

   public void delete(Carte carte){
       carteRepository.delete(carte);
    }
}

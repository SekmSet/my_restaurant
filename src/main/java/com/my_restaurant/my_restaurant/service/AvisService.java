package com.my_restaurant.my_restaurant.service;

import com.my_restaurant.my_restaurant.entity.Avis;
import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvisService {
    @Autowired
    private AvisRepository avisRepository;

    public List<Avis> findAll(){
        var results = avisRepository.findAll();

        var avis = new ArrayList<Avis>();
        results.forEach(avis::add);

        return avis;
    }

    public Avis findById(long id){
        return avisRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("L'avis n'a pas été trouvée"));
    }

    public List<Avis> getLast(long limit){
        var results = avisRepository.findOrderedBySeatNumberLimitedTo(limit);

        return new ArrayList<Avis>(results);
    }

    public Avis save(Avis avis) {
       return avisRepository.save(avis);
    }

    public void delete(Avis avis){
        avisRepository.delete(avis);
    }


}

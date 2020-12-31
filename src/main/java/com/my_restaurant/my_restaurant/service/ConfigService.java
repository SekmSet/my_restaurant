package com.my_restaurant.my_restaurant.service;
import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    public Config save(Config config) {
         return configRepository.save(config);
    }

    public Config findById(long id){
        return configRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("La config n'a pas été trouvée"));
    }

   public void delete(Config config){
        configRepository.delete(config);
    }

}

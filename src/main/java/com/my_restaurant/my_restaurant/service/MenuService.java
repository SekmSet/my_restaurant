package com.my_restaurant.my_restaurant.service;

import com.my_restaurant.my_restaurant.entity.Menu;
import com.my_restaurant.my_restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll(){
        var results = menuRepository.findAll();

        var menus = new ArrayList<Menu>();
        results.forEach(menus::add);

        return menus;
    }

    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu findById(long id){
        return menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Le menu n'a pas été trouvé"));
    }

   public void delete(Menu menu){
       menuRepository.delete(menu);
    }
}

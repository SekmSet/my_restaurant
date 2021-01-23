package com.my_restaurant.my_restaurant;

import com.my_restaurant.my_restaurant.entity.Config;
import com.my_restaurant.my_restaurant.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AddGlobalVariableToTemplates {

	@Autowired
	private ConfigService configService;

	// adds a global value to every model
	@ModelAttribute("colorButton")
	public String test() {
		Config config = findById();

		if(config.getBoutons().isBlank()){
			config.setBoutons("00FF00");
		}

		return config.getBoutons();
	}

	@ModelAttribute("colorLink")
	public String link() {
		Config config = findById();

		if(config.getLiens().isBlank()){
			config.setLiens("0F0F0F");
		}

		return config.getLiens();
	}

	private Config findById(){
		try {
			return configService.findById(1);
		} catch (Exception e) {
			return new Config();
		}
	}
}
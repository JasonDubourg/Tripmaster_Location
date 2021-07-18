package com.tripmaster.location;

import com.tripmaster.location.util.LocalizationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Locale;

@SpringBootApplication
public class LocationApplication {

	public static void main(String[] args) {
		LocalizationUtil localizationUtil = new LocalizationUtil();
		Locale.setDefault(localizationUtil.getUS_LOCALE());
		SpringApplication.run(LocationApplication.class, args);
	}

}

package com.znyar.imagetopdfconverter;

import com.znyar.imagetopdfconverter.storage.StorageProperties;
import com.znyar.imagetopdfconverter.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ImageToPdfConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageToPdfConverterApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}

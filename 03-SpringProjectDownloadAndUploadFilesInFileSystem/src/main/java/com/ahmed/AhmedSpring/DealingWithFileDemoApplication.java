package com.ahmed.AhmedSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DealingWithFileDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DealingWithFileDemoApplication.class, args);
	}
        
        // this demo is to store the files in a directory and store the file path in the database
        //when retrieve just get the path from db and retrieve it

}

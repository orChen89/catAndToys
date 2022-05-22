package com.catandtoys.catAndToys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CatAndToysApplication {

	public static void main(String[] args)  {

		ApplicationContext ctx = SpringApplication.run(CatAndToysApplication.class, args);

	}
}

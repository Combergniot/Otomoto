package com.concept.otomoto;

import com.concept.otomoto.data.CarOfferCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    CarOfferCollector carOfferCollector;

    public static void main(String[] args) {

        SpringApplication.run(Main.class);

    }

    @Override
    public void run(String... args) throws Exception {
        carOfferCollector.collectTestData();

    }
}

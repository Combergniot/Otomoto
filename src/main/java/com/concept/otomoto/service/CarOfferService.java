package com.concept.otomoto.service;

import com.concept.otomoto.model.CarOffer;
import com.concept.otomoto.repository.CarOfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarOfferService {

    private final CarOfferRepository carOfferRepository;

    public CarOfferService(CarOfferRepository carOfferRepository) {
        this.carOfferRepository = carOfferRepository;
    }


    public Iterable<CarOffer> list() {
        return carOfferRepository.findAll();
    }

    public CarOffer save(CarOffer carOffer) {
        return carOfferRepository.save(carOffer);
    }

    public void save(List<CarOffer> carOfferList) {
        carOfferRepository.save(carOfferList);
    }
}

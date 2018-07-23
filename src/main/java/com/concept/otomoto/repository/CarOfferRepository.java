package com.concept.otomoto.repository;

import com.concept.otomoto.model.CarOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOfferRepository extends CrudRepository<CarOffer, Long> {
}

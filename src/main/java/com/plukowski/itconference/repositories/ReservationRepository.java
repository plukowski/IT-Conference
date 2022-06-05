package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
}

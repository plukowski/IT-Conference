package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findByParticipantId(long id);
    List<Reservation> findByLectureId(long id);
}

package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Reservation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findByParticipantId(long id);
    List<Reservation> findByLectureId(long id);
    Reservation findByParticipantIdAndLectureId(long participantId, long lectureId);
    @Modifying
    @Transactional
    int deleteByParticipantIdAndLectureId(long participantId, long lectureId);
}

package com.plukowski.itconference.services;

import com.plukowski.itconference.controllers.ParticipantController;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    LectureRepository lectureRepository;
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    public long makeReservation(int subjectId, int period, Participant participant){
        if(participantRepository.findByLogin(participant.getLogin()) == null){
            if(participantRepository.findByEmail(participant.getEmail()) == null){
                participantRepository.save(participant);
                log.info("Dodano nowego użytkownika");
            }
            else{
                log.error("Podany adres e-mail jest już zajęty");
                return -1;
            }
        }
        participant = participantRepository.findByLogin(participant.getLogin());
        Lecture lecture = lectureRepository.findByPeriodAndSubjectId(period, subjectId);
        if(lecture == null){
            log.error("Podana prelekcja nie istnieje");
            return -2;
        }
        else{
            boolean done = false;
            List<Reservation> madeReservations = reservationRepository.findByParticipantId(participant.getId());
            for(Reservation reservation: madeReservations){
                if(lectureRepository.findById(reservation.getLectureId()).get().getPeriod() == lecture.getPeriod()){
                    log.error("Użytkownik uczestniczy już w prelekcji o tej porze");
                    return -4;
                }
            }
            if(lecture.getSlots() < 1){
                //TODO współbieżność
                log.error("Nie ma już miejsc na podanej prelekcji");
                return -3;
            }
            else {
                lectureRepository.decrementSlots(period, subjectId);
                reservationRepository.save(new Reservation(lecture.getId(),participant.getId()));
                log.info("Dodano rezerwację");
                //TODO powiadomienie
                return 0;
            }
        }
    }
}

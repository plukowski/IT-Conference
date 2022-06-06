package com.plukowski.itconference.services;

import com.plukowski.itconference.controllers.ParticipantController;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    LectureRepository lectureRepository;
    private static final Logger log = LoggerFactory.getLogger(ParticipantService.class);

    public String getUserReservations(String login){
        List<Reservation> reservations = reservationRepository.findByParticipantId(
                participantRepository.findByLogin(login).getId()
        );
        String result = "";
        for(Reservation reservation: reservations){
            result += lectureRepository.findById(reservation.getLectureId()).toString()+"\n";
        }
        return result;
    }

    public long insertNewParticipant(Participant participant){
        if(participantRepository.findByLogin(participant.getLogin()) != null){
            log.error("Podany login jest już zajęty");
            return -1;
        }
        else if(participantRepository.findByEmail(participant.getEmail())!= null){
            log.error("Podany adres e-mail jest już zajęty");
            return -2;
        }
        else{
            log.info("Dodano nowego użytkownika");
            return participantRepository.save(participant).getId();
        }
    }

    public long changeParticipantEmail(Participant participant){
        if(participantRepository.findByLogin(participant.getLogin()) == null){
            log.error("Podany użytkownik nie istnieje");
            return -1;
        }
        else if(participantRepository.findByEmail(participant.getEmail())!= null){
            log.error("Podany adres e-mail jest już zajęty");
            return -2;
        }
        else{
            log.info("Zaktualizowano adres e-mail użytkownika "+participant.getLogin());
            return participantRepository.updateEmail(participant.getLogin(),participant.getEmail());
        }
    }

    public String showUsers(){
        String result = "";
        for (Participant participant : participantRepository.findAll()) {
            result += participant.toString()+"\n";
        }
        return  result;
    }
}

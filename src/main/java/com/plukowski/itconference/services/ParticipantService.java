package com.plukowski.itconference.services;

import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void insertNewParticipant(Participant participant) throws ExceptionWithMessage {
        if(participantRepository.findByLogin(participant.getLogin()) != null){
            String message = "Podany login jest już zajęty";
            log.error(message);
            throw new ExceptionWithMessage(message);
        }
        else if(participantRepository.findByEmail(participant.getEmail())!= null){
            String message = "Podany adres e-mail jest już zajęty";
            log.error(message);
            throw new ExceptionWithMessage(message);
        }
        else{
            log.info("Dodano nowego użytkownika");
            participantRepository.save(participant);
        }
    }

    public void changeParticipantEmail(Participant participant) throws ExceptionWithMessage {
        if(participantRepository.findByLogin(participant.getLogin()) == null){
            String message = "Podany użytkownik nie istnieje";
            throw new ExceptionWithMessage(message);
        }
        else if(participantRepository.findByEmail(participant.getEmail())!= null){
            String message = "Podany adres e-mail jest już zajęty";
            throw new ExceptionWithMessage(message);
        }
        else{
            log.info("Zaktualizowano adres e-mail użytkownika "+participant.getLogin());
            participantRepository.updateEmail(participant.getLogin(),participant.getEmail());
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

package com.plukowski.itconference.services;

import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
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
    public void insert(Participant participant){
        participantRepository.save(participant);
    }
    public Participant findById(long id){
        return participantRepository.findById(id).get();
    }
    public Participant findByLogin(String login){
        return participantRepository.findByLogin(login);
    }
    public Participant findByEmail(String email){
        return participantRepository.findByEmail(email);
    }
    public int updateEmail(String login, String email){
        if(participantRepository.findByLogin(login) != null){
            return participantRepository.updateEmail(login,email);
        }
        else{
            return 0;
        }
    }
    public List<Reservation> getUserReservations(String login){
        return reservationRepository.findByParticipantId(
                participantRepository.findByLogin(login).getId()
        );
    }
}

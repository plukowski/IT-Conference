package com.plukowski.itconference.controllers;

import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.services.ParticipantService;
import com.plukowski.itconference.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParticipantController {
    @Autowired
    ParticipantService participantService;

    private static final Logger log = LoggerFactory.getLogger(ParticipantController.class);

    //Dodanie nowego użytkownika
    @PostMapping("/user")
    public void createUser(@RequestBody Participant participant) {
        if (participantService.findByLogin(participant.getLogin()) != null) {
            log.error("Podany login jest już zajęty");
        } else if (participantService.findByEmail(participant.getEmail()) != null) {
            log.error("Podany adres e-mail jest już zajęty");
        } else {
            participantService.insert(participant);
            log.info("Dodano nowego użytkownika");
        }
        //TODO response
    }

    //Zmiana e-maila użytkownika
    @PatchMapping("/user")
    public void changeEmail(@RequestBody Participant participant) {
        if(participantService.updateEmail(participant.getLogin(),participant.getEmail()) == 0){
            log.error("Nie istnieje użytkownik o podanym loginie");
        }
        else{
            log.info("Zmieniono adres e-mail użytkownika "+participant.getLogin());
        }
        //TODO response
    }

    //Wyświetlenie rezerwacji użytkownika
    @GetMapping("/user")
    public List<Reservation> getUserReservations(@RequestParam(value = "login") String login){
        return participantService.getUserReservations(login);
    }
}

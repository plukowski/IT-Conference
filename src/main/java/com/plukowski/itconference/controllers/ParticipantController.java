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
        long result = participantService.insertNewParticipant(participant);
        //TODO response
    }

    //Zmiana e-maila użytkownika
    @PatchMapping("/user")
    public void changeEmail(@RequestBody Participant participant) {
        long result = participantService.changeParticipantEmail(participant);
        //TODO response
    }

    //Wyświetlenie rezerwacji użytkownika
    @GetMapping("/user")
    public List<Reservation> getUserReservations(@RequestParam(value = "login") String login){
        return participantService.getUserReservations(login);
        //TODO response
    }
}

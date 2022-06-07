package com.plukowski.itconference.controllers;

import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.services.ParticipantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParticipantController {
    @Autowired
    ParticipantService participantService;

    private static final Logger log = LoggerFactory.getLogger(ParticipantController.class);

    @ExceptionHandler(ExceptionWithMessage.class)
    public ResponseEntity<String> handleException(ExceptionWithMessage exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Dodanie nowego użytkownika
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody Participant participant) throws ExceptionWithMessage {
        participantService.insertNewParticipant(participant);
        return new ResponseEntity<>("Dodano nowego użytkownika", HttpStatus.OK);
    }

    //Zmiana e-maila użytkownika
    @PatchMapping("/user")
    public ResponseEntity<String> changeEmail(@RequestBody Participant participant) throws ExceptionWithMessage {
        participantService.changeParticipantEmail(participant);
        return new ResponseEntity<>("Zmieniono adres e-mail", HttpStatus.OK);
    }

    //Wyświetlenie rezerwacji użytkownika
    @GetMapping("/user")
    public ResponseEntity<String> getUserReservations(@RequestParam(value = "login") String login) {
        return new ResponseEntity<>(participantService.getUserReservations(login), HttpStatus.OK);
    }

    //Wyświetlenie listy użytkowników
    @GetMapping("/user/all")
    public ResponseEntity<String> showUsers() {
        return new ResponseEntity<>(participantService.showUsers(), HttpStatus.OK);
    }
}

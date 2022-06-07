package com.plukowski.itconference.controllers;

import ch.qos.logback.core.pattern.SpacePadder;
import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @ExceptionHandler(ExceptionWithMessage.class)
    public ResponseEntity<String> handleException(ExceptionWithMessage exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //Rejestracja użytkownika i rezerwacja miejsca na prelekcji
    @PostMapping("/reservation")
    public ResponseEntity<String> reserveSlot(@RequestParam(value = "subjectId") int subjectId,
                                                   @RequestParam(value = "period") int period, @RequestBody Participant participant) throws ExceptionWithMessage {
        reservationService.makeReservation(subjectId,period,participant);
        return new ResponseEntity<>("Poprawie złożono rezerwację",HttpStatus.OK);
    }
    @DeleteMapping("/reservation")
    public ResponseEntity<String> deleteReservation(@RequestParam(value = "subjectId") int subjectId,
                                  @RequestParam(value = "period") int period, @RequestBody Participant participant) throws ExceptionWithMessage {
        reservationService.deleteReservation(subjectId,period,participant);
        return new ResponseEntity<>("Odwołano rezerwację",HttpStatus.OK);
    }
}

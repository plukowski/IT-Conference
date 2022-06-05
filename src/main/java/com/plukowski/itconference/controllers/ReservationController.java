package com.plukowski.itconference.controllers;

import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    //Rejestracja użytkownika i rezerwacja miejsca na prelekcji
    @PostMapping("/reservation")
    public void reserveSlot(@RequestParam(value = "subjectId") int subjectId, @RequestParam(value = "period") int period, @RequestBody Participant participant){
        reservationService.makeReservation(subjectId,period,participant);
        //TODO response
    }
}

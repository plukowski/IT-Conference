package com.plukowski.itconference.services;

import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

}

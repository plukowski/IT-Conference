package com.plukowski.itconference.services;

import com.plukowski.itconference.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository participantRepository;
}

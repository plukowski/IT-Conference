package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}

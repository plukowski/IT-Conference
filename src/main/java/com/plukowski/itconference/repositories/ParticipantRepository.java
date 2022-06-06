package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Participant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    Participant findByLogin(String login);
    Participant findByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE Participant p SET p.email = ?2 WHERE p.login = ?1")
    int updateEmail(String login, String email);
}

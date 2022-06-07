package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Lecture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
    List<Lecture> findBySubjectId(int id);
    List<Lecture> findByPeriod(int period);
    Lecture findByPeriodAndSubjectId(int period, int subjectId);

    @Modifying
    @Transactional
    @Query("UPDATE Lecture l SET l.slots = l.slots-1 WHERE l.period = ?1 AND l.subjectId = ?2")
    int decrementSlots(int period, int subjectId);

    @Modifying
    @Transactional
    @Query("UPDATE Lecture l SET l.slots = l.slots+1 WHERE l.period = ?1 AND l.subjectId = ?2")
    int incrementSlots(int period, int subjectId);
}

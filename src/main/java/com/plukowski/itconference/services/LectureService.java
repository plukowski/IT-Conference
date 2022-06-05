package com.plukowski.itconference.services;

import com.plukowski.itconference.controllers.ParticipantController;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.repositories.LectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    @Autowired
    LectureRepository lectureRepository;
    private static final Logger log = LoggerFactory.getLogger(LectureService.class);
    public long insert(Lecture lecture){
        return lectureRepository.save(lecture).getId();
    }

}

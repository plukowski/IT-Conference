package com.plukowski.itconference.services;

import com.plukowski.itconference.Schedule;
import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    ReservationRepository reservationRepository;
    private static final Logger log = LoggerFactory.getLogger(LectureService.class);

    public long insert(Lecture lecture){
        return lectureRepository.save(lecture).getId();
    }

    public String getAllLectures(){
        String result = "";
        for (Lecture lecture : lectureRepository.findAll()) {
            result += lecture.toString();
        }
        return result;
    }

    public String getStats(int type) throws ExceptionWithMessage {
        long allParticipants = participantRepository.count();
        String result = "";
        if(type == 0) {
            for (Lecture lecture : lectureRepository.findAll()) {
                int participants = 5 - lecture.getSlots();
                float interest = ((float) participants / (float) allParticipants * 100);
                result += lecture.toString() + " interest=" + interest + "%\n";
            }
            return result;
        }
        else if (type == 1){
            for(int subject: Schedule.subjects.keySet()){
                int participants = 0;
                for(Lecture lecture: lectureRepository.findBySubjectId(subject)){
                    participants += 5-lecture.getSlots();
                }
                float interest = ((float) participants / (float) allParticipants * 100);
                result += "Subject="+Schedule.subjects.get(subject)+" interest="+interest+"%\n";
            }
            return result;
        }
        else
        throw new ExceptionWithMessage("Nieznany typ statystyk");
    }
}

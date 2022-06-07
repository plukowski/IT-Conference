package com.plukowski.itconference.services;

import com.plukowski.itconference.Schedule;
import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.ArrayList;

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
            ArrayList<Integer> participants = new ArrayList<>();
            for(int subject: Schedule.subjects.keySet()){
                participants.add(0);
            }
            for(Participant participant: participantRepository.findAll()){
                ArrayList<Integer> alreadyCountedSubjects = new ArrayList<>();
                for(Reservation reservation: reservationRepository.findByParticipantId(participant.getId())){
                    int index = (lectureRepository.findById(reservation.getLectureId()).get().getSubjectId()) - 1;
                    if(alreadyCountedSubjects.contains(index))
                        continue;
                    alreadyCountedSubjects.add(index);
                    participants.set(index,participants.get(index)+1);
                }
            }
            for(int subject: Schedule.subjects.keySet()){
                float interest = ((float) participants.get(subject-1) / (float) allParticipants * 100);
                result += "Subject="+Schedule.subjects.get(subject)+" interest="+interest+"%\n";
            }
            return result;
        }
        else
        throw new ExceptionWithMessage("Nieznany typ statystyk");
    }
}

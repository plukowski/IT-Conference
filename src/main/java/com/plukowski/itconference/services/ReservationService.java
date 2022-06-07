package com.plukowski.itconference.services;

import com.plukowski.itconference.Schedule;
import com.plukowski.itconference.controllers.ParticipantController;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.models.Participant;
import com.plukowski.itconference.models.Reservation;
import com.plukowski.itconference.repositories.LectureRepository;
import com.plukowski.itconference.repositories.ParticipantRepository;
import com.plukowski.itconference.repositories.ReservationRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    LectureRepository lectureRepository;
    private static final Logger log = LoggerFactory.getLogger(ReservationService.class);
    public long makeReservation(int subjectId, int period, Participant participant){
        if(participantRepository.findByLogin(participant.getLogin()) == null){
            if(participantRepository.findByEmail(participant.getEmail()) == null){
                participantRepository.save(participant);
                log.info("Dodano nowego użytkownika");
            }
            else{
                log.error("Podany adres e-mail jest już zajęty");
                return -1;
            }
        }
        participant = participantRepository.findByLogin(participant.getLogin());
        Lecture lecture = lectureRepository.findByPeriodAndSubjectId(period, subjectId);
        if(lecture == null){
            log.error("Podana prelekcja nie istnieje");
            return -2;
        }
        else{
            boolean done = false;
            List<Reservation> madeReservations = reservationRepository.findByParticipantId(participant.getId());
            for(Reservation reservation: madeReservations){
                if(lectureRepository.findById(reservation.getLectureId()).get().getPeriod() == lecture.getPeriod()){
                    log.error("Użytkownik uczestniczy już w prelekcji o tej porze");
                    return -4;
                }
            }
            if(lecture.getSlots() < 1){
                //TODO współbieżność
                log.error("Nie ma już miejsc na podanej prelekcji");
                return -3;
            }
            else {
                lectureRepository.decrementSlots(period, subjectId);
                Reservation reservation = new Reservation(lecture.getId(),participant.getId());
                reservationRepository.save(reservation);
                log.info("Dodano rezerwację");
                //"Wysyłanie" powiadomienia
                sendNotification(participant,reservation, LocalDateTime.now());


                return 0;
            }
        }

    }
    public void sendNotification(Participant user, Reservation reservation, LocalDateTime dateTime){
        Lecture lecture = lectureRepository.findById(reservation.getLectureId()).get();
        String subject = Schedule.subjects.get(lecture.getSubjectId());
        String startTime = Schedule.periods.get(lecture.getPeriod()).get(0).format(DateTimeFormatter.ISO_DATE_TIME);
        String endTime = Schedule.periods.get(lecture.getPeriod()).get(1).format(DateTimeFormatter.ISO_DATE_TIME);
        String notification = "Date: "+dateTime.format(DateTimeFormatter.ISO_DATE_TIME)+"\nTo: "+user.getEmail()+"\nMessage: Drogi "+user.getLogin()+
                ",\nDokonałeś rezerwacji na prelekcję o tematyce "+subject+", która odbędzie się "+startTime+" i potrwa do "+endTime+".\nZ poważaniem\nZespół IT-Conference";
        Path path = Paths.get("notifications/"+user.getLogin()+"-notification.txt");
        try {
            if(Files.exists(path)){
                Files.delete(path);
            }
            Files.createFile(path);
            Files.writeString(path,notification,StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    public int deleteReservation(int subjectId, int period, Participant participant){
        participant = participantRepository.findByLogin(participant.getLogin());
        int result = reservationRepository.deleteByParticipantIdAndLectureId(participant.getId(),
                lectureRepository.findByPeriodAndSubjectId(period,subjectId).getId());
        if(result > 0){
            log.info("Odwołano rezerwację");
            lectureRepository.incrementSlots(period,subjectId);
            return result;
        }
        else{
            log.error("Nie można odwołać rezerwacji");
            return result;
        }
    }
}

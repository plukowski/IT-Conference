package com.plukowski.itconference;

import com.plukowski.itconference.controllers.ParticipantController;
import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.repositories.LectureRepository;
import org.hibernate.engine.jdbc.env.internal.DefaultSchemaNameResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class ItConferenceApplication {

    private static final Logger log = LoggerFactory.getLogger(ItConferenceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ItConferenceApplication.class, args);
    }

    @Bean
    public CommandLineRunner lectureFiller(LectureRepository lectureRepository) {
        return (args) -> {
            //Wypełnianie tabeli prelekcji
            for (int subjectId = 1; subjectId < 4; subjectId++) {
                for (int period = 1; period < 4; period++) {
                    lectureRepository.save(new Lecture(period,subjectId));
                }
            }
        };
    }
    @Bean
    public CommandLineRunner scheduleFiller(){
        return(args)->{
            //Wypełnianie godzin prelekcji i nazw ścieżek tematycznych
            ArrayList<LocalDateTime> firstPeriodDateTime = new ArrayList<>();
            firstPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,10,0));
            firstPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,11,45));
            Schedule.periods.put(1,firstPeriodDateTime);
            ArrayList<LocalDateTime> secondPeriodDateTime = new ArrayList<>();
            secondPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,12,0));
            secondPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,13,45));
            Schedule.periods.put(2,secondPeriodDateTime);
            ArrayList<LocalDateTime> thirdPeriodDateTime = new ArrayList<>();
            thirdPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,14,0));
            thirdPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,15,45));
            Schedule.periods.put(3,thirdPeriodDateTime);
            Schedule.subjects.put(1,"Front-end");
            Schedule.subjects.put(2,"Back-end");
            Schedule.subjects.put(3,"UX");
        };
    }
}

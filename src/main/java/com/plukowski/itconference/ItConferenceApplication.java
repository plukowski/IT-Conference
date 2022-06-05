package com.plukowski.itconference;

import com.plukowski.itconference.models.Lecture;
import com.plukowski.itconference.repositories.LectureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class ItConferenceApplication {

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
}

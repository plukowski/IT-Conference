package com.plukowski.itconference.controllers;

import com.plukowski.itconference.exceptions.ExceptionWithMessage;
import com.plukowski.itconference.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {
    @Autowired
    LectureService lectureService;

    @ExceptionHandler(ExceptionWithMessage.class)
    public ResponseEntity<String> handleException(ExceptionWithMessage exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/lecture/all")
    public ResponseEntity<String> showAllLectures(){
        return new ResponseEntity<>(lectureService.getAllLectures(),HttpStatus.OK);
    }
    @GetMapping("/lecture/stats")
    public ResponseEntity<String> getStats(@RequestParam(value = "type") int type) throws ExceptionWithMessage {
        return new ResponseEntity<>(lectureService.getStats(type),HttpStatus.OK);
    }
}

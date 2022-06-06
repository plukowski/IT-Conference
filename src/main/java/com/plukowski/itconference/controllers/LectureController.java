package com.plukowski.itconference.controllers;

import com.plukowski.itconference.services.LectureService;
import com.plukowski.itconference.services.ReservationService;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {
    @Autowired
    LectureService lectureService;


    @GetMapping("/lecture/all")
    public String showAllLectures(){
        return lectureService.getAllLectures();
    }
    @GetMapping("/lecture/stats")
    public String getStats(@RequestParam(value = "type") int type){
        return lectureService.getStats(type);
    }
}

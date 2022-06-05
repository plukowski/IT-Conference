package com.plukowski.itconference.controllers;

import com.plukowski.itconference.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {
    @Autowired
    LectureService lectureService;
}

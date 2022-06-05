package com.plukowski.itconference.repositories;

import com.plukowski.itconference.models.Lecture;
import org.springframework.data.repository.CrudRepository;

public interface LectureRepository extends CrudRepository<Lecture,Long> {
}

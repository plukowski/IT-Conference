package com.plukowski.itconference.models;

import com.plukowski.itconference.Schedule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int period;
    private int subjectId;
    private int slots;

    protected Lecture() {}
    public Lecture(int period, int subjectId){
        this.period = period;
        this.subjectId = subjectId;
        this.slots = 5;
    }

    public long getId() {
        return id;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return String.format("Lecture[start=%s, end=%s, subject=%s]",Schedule.periods.get(this.period).get(0).format(formatter),
                Schedule.periods.get(this.period).get(1).format(formatter),Schedule.subjects.get(this.subjectId));
    }
}

package com.plukowski.itconference;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Schedule {
    //Mapowanie numeru jednostki do jej daty rozpoczęcia i zakończenia
    private static final Map<Integer, ArrayList<LocalDateTime>> periods = new TreeMap<>();
    static{
        ArrayList<LocalDateTime> firstPeriodDateTime = new ArrayList<>();
        firstPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,10,0));
        firstPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,11,45));
        periods.put(1,firstPeriodDateTime);
        ArrayList<LocalDateTime> secondPeriodDateTime = new ArrayList<>();
        secondPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,12,0));
        secondPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,13,45));
        periods.put(1,secondPeriodDateTime);
        ArrayList<LocalDateTime> thirdPeriodDateTime = new ArrayList<>();
        thirdPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,14,0));
        thirdPeriodDateTime.add(LocalDateTime.of(2021, Month.JUNE,1,15,45));
        periods.put(1,thirdPeriodDateTime);
    }

//    //Pierwsza jednostka
//    public static LocalDateTime firstPeriodStart = LocalDateTime.of(2021, Month.JUNE,1,10,0);
//    public static LocalDateTime firstPeriodEnd = LocalDateTime.of(2021, Month.JUNE,1,11,45);
//    //Druga jednostka
//    public static LocalDateTime secondPeriodStart = LocalDateTime.of(2021, Month.JUNE,1,12,0);
//    public static LocalDateTime secondPeriodEnd = LocalDateTime.of(2021, Month.JUNE,1,13,45);
//    //Trzecia jednostka
//    public static LocalDateTime thirdPeriodStart = LocalDateTime.of(2021, Month.JUNE,1,14,0);
//    public static LocalDateTime thirdPeriodEnd = LocalDateTime.of(2021, Month.JUNE,1,15,45);
}

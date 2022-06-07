package com.plukowski.itconference;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Schedule {
    //Mapowanie numeru jednostki do jej daty rozpoczęcia i zakończenia
    public static Map<Integer, ArrayList<LocalDateTime>> periods = new TreeMap<>();

    //Mapowanie numeru tematu prelekcji do jego nazwy
    public static Map<Integer, String> subjects = new TreeMap<>();


}

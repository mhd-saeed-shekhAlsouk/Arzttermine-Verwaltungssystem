package org.example.arzttermin.Model;

import java.util.ArrayList;

public class AvailabilityCalendar {
    private String date;
    private ArrayList<String> availablityTimes;

    private AvailabilityCalendar(){
        availablityTimes = new ArrayList<>();
    }

    public AvailabilityCalendar(String dateInput){
        availablityTimes = new ArrayList<>();
        date = dateInput;
        availablityTimes.add("9:00");
        availablityTimes.add("10:00");
        availablityTimes.add("11:00");
        availablityTimes.add("12:00");
        availablityTimes.add("13:00");
        availablityTimes.add("14:00");
        availablityTimes.add("15:00");
        availablityTimes.add("16:00");
        availablityTimes.add("17:00");
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getAvailablityTimes() {
        return availablityTimes;
    }

    public void setAvailablityTimes(ArrayList<String> availablityTimes) {
        this.availablityTimes = availablityTimes;
    }

    @Override
    public String toString() {
        return "AvailabilityCalendar{" +
                "date='" + date + '\'' +
                ", availablityTimes=" + availablityTimes +
                '}';
    }
}

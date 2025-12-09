package Model;

import java.util.ArrayList;

public class AvailabilityCalendar {
    private String date;
    private ArrayList<String> availabilityTimes;

    public AvailabilityCalendar(String date, int openTime, int closeTime) {
        this.date = date;
        availabilityTimes = new ArrayList<>();
        for (int i = 0; i < closeTime; i++) {
            availabilityTimes.add( Integer.toString(openTime + i) + ":00 Uhr");
            availabilityTimes.add( Integer.toString(openTime + i) + ":30 Uhr");
        }
    }

    public String getDate() {return date;}
}

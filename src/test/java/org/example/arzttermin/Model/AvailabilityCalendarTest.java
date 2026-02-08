package org.example.arzttermin.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AvailabilityCalendarTest {

    @Test
    void constructorInitializesWithDefaultTimes() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        ArrayList<String> expectedTimes = new ArrayList<>();
        expectedTimes.add("9:00");
        expectedTimes.add("10:00");
        expectedTimes.add("11:00");
        expectedTimes.add("12:00");
        expectedTimes.add("13:00");
        expectedTimes.add("14:00");
        expectedTimes.add("15:00");
        expectedTimes.add("16:00");
        expectedTimes.add("17:00");

        assertEquals("12-02-2026", calendar.getDate());
        assertEquals(expectedTimes, calendar.getAvailablityTimes());
    }

    @Test
    void getDate() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        assertEquals("12-02-2026", calendar.getDate());
    }

    @Test
    void setDate() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        calendar.setDate("12-03-2026");
        assertEquals("12-03-2026", calendar.getDate());
    }

    @Test
    void getAvailablityTimes() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        ArrayList<String> expectedTimes = new ArrayList<>();
        expectedTimes.add("9:00");
        expectedTimes.add("10:00");
        expectedTimes.add("11:00");
        expectedTimes.add("12:00");
        expectedTimes.add("13:00");
        expectedTimes.add("14:00");
        expectedTimes.add("15:00");
        expectedTimes.add("16:00");
        expectedTimes.add("17:00");

        assertEquals(expectedTimes, calendar.getAvailablityTimes());
    }

    @Test
    void setAvailablityTimes() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        ArrayList<String> newTimes = new ArrayList<>();
        newTimes.add("18:00");
        newTimes.add("19:00");

        calendar.setAvailablityTimes(newTimes);
        assertEquals(newTimes, calendar.getAvailablityTimes());
    }

    @Test
    void testToString() {
        AvailabilityCalendar calendar = new AvailabilityCalendar("12-02-2026");
        String expectedString = "AvailabilityCalendar{date='12-02-2026', availablityTimes=[9:00, 10:00, 11:00, 12:00, 13:00, 14:00, 15:00, 16:00, 17:00]}";
        assertEquals(expectedString, calendar.toString());
    }
}

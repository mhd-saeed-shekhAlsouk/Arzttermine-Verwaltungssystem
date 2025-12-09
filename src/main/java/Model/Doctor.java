package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Doctor extends User {
    int shiftBegin;
    int shiftEnd;

    public  Doctor(int id, String firstName, String lastName, String username, String password, Date dob, String email, String gender, boolean isActive, String city, int shiftBegin, int shiftEnd) {
        super(id, firstName, lastName, username, password, "Patient", dob, email, gender, isActive);
        this.shiftBegin = shiftBegin;
        this.shiftEnd = shiftEnd;
        this.setAvailability(new ArrayList<>());
        for (int i = 1; i <= 30; i++) {
            getAvailability().add(new AvailabilityCalendar(i + "-12-2025", shiftBegin, shiftEnd));
        }
        setCity(city);
    }
}

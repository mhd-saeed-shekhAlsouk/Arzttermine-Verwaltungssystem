package Model;

import java.sql.Date;

public class Patient extends User{
    public  Patient(int id, String firstName, String lastName, String username, String password, Date dob, String email, String gender, boolean isActive) {
        super(id, firstName, lastName, username, password, "Patient", dob, email, gender, isActive);
    }
}

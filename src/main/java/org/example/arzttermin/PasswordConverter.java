package org.example.arzttermin;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class PasswordConverter {

    public static void main(String[] args) {
        String password;
        String hashedPassword;

        while (true) {
            System.out.println("Password:");
            Scanner sc = new Scanner(System.in);
            password = sc.nextLine();
            hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            System.out.println(hashedPassword);
        }
    }
}

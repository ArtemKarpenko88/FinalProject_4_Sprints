package org.praktikumserices.customerData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TheCurrentDate {
    public static String theCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate date = LocalDate.now();
        String theCurrentDate = dtf.format(date);
        return theCurrentDate;
    }
}

package com.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

/**
 * This class handles Validation.
 */
public class Validation {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This method is used to display the employee.
     * @param 
     *    - accept the string value
     */
    public boolean stringValidation(String str) {
        String regex = "^[a-zA-Z]+([ ][a-zA-Z]+)*$";
        return Pattern.matches(regex, str);
    }

    /**
     * This method is used to validate the name.
     */
    public String nameValidation() {
        String name = "";
        while (true) {
            System.out.println("Enter valid Name: ");
            name = scanner.nextLine();
            if (stringValidation(name)) {
                break;
            }
        }
        return name;
    }

    /**
     * This method is used to validate the place.
     */
    public String placeValidation() {
        String place = "";
        while (true) {
            System.out.println("Enter valid Place: ");
            place = scanner.nextLine();
            if (stringValidation(place)) {
                break;
            }
        }
        return place;
    }

    /**
     * This method is used to validate the experience.
     * @param integer
     *    - accepts expeience
     */
    public boolean experienceValidation(int experience) {
        return experience >= 0;
    }

    /**
     * This method is used to validate the Date of Birth.
     * @param string
     *    - accepts Date of Birth as a string
     */
    public LocalDate dateOfBirthValidation(String dobString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                LocalDate dob = LocalDate.parse(dobString, dateFormatter);
                if (dob.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Date of birth cannot be in the future.");
                }
                return dob;
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println("Invalid date format or date in the future. Please enter a valid date (DD/MM/YYYY): ");
                dobString = scanner.nextLine();
            }
        }
    }
}
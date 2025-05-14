package org.example;

/**
 * Represents a teacher in the school management system.
 */
public class Teacher {
    private String firstName;
    private String lastName;
    private String subject;

    /**
     * Constructs a new teacher with the specified details.
     */
    public Teacher(String firstName, String lastName, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + subject + ")";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}

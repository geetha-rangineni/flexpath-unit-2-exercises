package org.example;

/**
 * Student class to represent a student
 */
public class Student {
    // TODO: Add instance variables
    // firstName (String)
    // lastName (String)
    // classNumber (int)
    // className (String)
    private String firstName;
    private String lastName;
    private int classNumber;
    private String className;

    // TODO: Implement correct code for getters/setters

    /**
     * Get the first name of the student
     *
     * @return first name of the student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the student
     *
     * @param firstName first name of the student
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the student
     *
     * @return last name of the student
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the student
     *
     * @param lastName last name of the student
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the class number of the student
     *
     * @return class number of the student
     */
    public int getClassNumber() {
        return classNumber;
    }

    /**
     * Set the class number of the student
     *
     * @param classNumber class number of the student
     */
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    /**
     * Get the class name of the student
     *
     * @return class name of the student
     */
    public String getClassName() {
        return className;
    }

    /**
     * Set the class name of the student
     *
     * @param className class name of the student
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Constructor to create a new student
     *
     * @param firstName first name of the student
     * @param lastName last name of the student
     * @param classNumber class number of the student
     * @param className class name of the student
     */
    public Student(String firstName, String lastName, int classNumber, String className) {
        // TODO: Set instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.classNumber = classNumber;
        this.className = className;
    }

    /**
     * Return a string representation of the student
     *
     * @return string representation of the student
     */
    public String toString() {
        // TODO: Return a string representation of the student in this format:
        // firstName lastName (classNumber - className)
        return firstName + " " + lastName + " (" + classNumber + " - " + className + ")";
    }
}

package org.example;

import java.util.*;

public class Main {

    // Returns a list of student names
    public static List<String> getStudentNamesList() {
        return new ArrayList<>(List.of("John", "Jane", "Alice", "Bob"));
    }

    // Filters names starting with 'M'
    public static List<String> getMStudents(List<String> students) {
        List<String> result = new ArrayList<>();
        for (String student : students) {
            if (student.startsWith("M")) {
                result.add(student);
            }
        }
        return result;
    }

    // Gets the first three items from a queue
    public static List<String> getFirstThreeItems(Queue<String> queue) {
        List<String> result = new ArrayList<>();
        int count = 0;
        for (String item : queue) {
            if (count >= 3) break;
            result.add(item);
            count++;
        }
        return result;
    }

    // Converts a list to a set (removing duplicates)
    public static Set<String> makeSetFromList(List<String> students) {
        return new HashSet<>(students);
    }

    // Checks if any elements from needles are in haystack
    public static boolean checkIfHashSetContainsAny(Set<String> haystack, List<String> needles) {
        for (String needle : needles) {
            if (haystack.contains(needle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a map of student names to grades.
     *
     * @param grades a list of student grades in order
     * @param students a list of student names in order
     * @return a map of student names to grades
     */
    public static HashMap<String, Double> buildStudentGradesMap(List<String> students, List<Double> grades) {
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 0; i < students.size(); i++) {
            map.put(students.get(i), grades.get(i));
        }

        return map;
    }

}
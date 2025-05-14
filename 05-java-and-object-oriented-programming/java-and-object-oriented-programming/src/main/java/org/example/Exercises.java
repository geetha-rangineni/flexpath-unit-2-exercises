package org.example;

public class Exercises {

    // EXERCISE 1: Add two plus two
    public int addTwoPlusTwo() {
        return 2 + 2;
    }

    // EXERCISE 2: Multiply pi by 5
    public double multiplyPiBy5() {
        double pi = 3.14;
        return pi * 5;
    }

    // EXERCISE 3: Concatenate name and age
    public String concatenateNameAndAge() {
        String name = "Alice";
        int age = 25;
        return name + age;
    }

    // EXERCISE 4: Continue or End
    public String continueOrEnd() {
        String result = "";
        String prompt = "Do you want to continue?";
        String userInput = "Yes";
        if (userInput.equals("Yes")) {
            result = "The program will continue";
        } else {
            result = "The program will end";
        }
        return result;
    }

    // EXERCISE 5: Store index in array
    public int[] storeIndexInArray() {
        int[] results = {0, 0, 0};
        for (int i = 0; i < results.length; i++) {
            results[i] = i;
        }
        return results;
    }

    // EXERCISE 6: Count correct answers
    public int countCorrect() {
        String prompt = "What is our favorite language?";
        String correctAnswer = "Java";
        String[] studentAnswers = {"Python", "Java", "C++"};
        int result = 0;
        for (String answer : studentAnswers) {
            if (answer.equals(correctAnswer)) {
                result++;
            }
        }
        return result;
    }

    // EXERCISE 7: Multiply two integers
    public int multiply(int x, int y) {
        return x * y;
    }

    // EXERCISE 8: Sum array
    public int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
}

package com.codealpha.studentgradetracker;

import java.util.Arrays;

/**
 * Represents one student and their subject marks.
 */
public class Student {
    private final int rollNumber;
    private String name;
    private final double[] marks;

    public Student(int rollNumber, String name, double[] marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = Arrays.copyOf(marks, marks.length);
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getMarks() {
        return Arrays.copyOf(marks, marks.length);
    }

    public double getTotal() {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    public double getAverage() {
        return getTotal() / marks.length;
    }

    public double getHighestScore() {
        double highest = marks[0];
        for (double mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    public double getLowestScore() {
        double lowest = marks[0];
        for (double mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        if (average >= 40) return "E";
        return "F";
    }

    public boolean isPassed() {
        return getAverage() >= 40;
    }

    public void updateMarks(double[] newMarks) {
        if (newMarks.length != marks.length) {
            throw new IllegalArgumentException("Number of subjects cannot be changed.");
        }

        System.arraycopy(newMarks, 0, marks, 0, marks.length);
    }

    @Override
    public String toString() {
        return String.format(
                "%-8d %-22s %-10.2f %-10.2f %-8s %-8s",
                rollNumber,
                name,
                getTotal(),
                getAverage(),
                getGrade(),
                isPassed() ? "PASS" : "FAIL"
        );
    }
}

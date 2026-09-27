package com.codealpha.studentgradetracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles student records and class-level calculations.
 */
public class GradeTracker {
    private final ArrayList<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (findByRollNumber(student.getRollNumber()) != null) {
            return false;
        }
        students.add(student);
        return true;
    }

    public Student findByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public boolean deleteStudent(int rollNumber) {
        Student student = findByRollNumber(rollNumber);
        if (student == null) {
            return false;
        }
        students.remove(student);
        return true;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public int getStudentCount() {
        return students.size();
    }

    public double getClassAverage() {
        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;
        for (Student student : students) {
            total += student.getAverage();
        }
        return total / students.size();
    }

    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student top = students.get(0);
        for (Student student : students) {
            if (student.getAverage() > top.getAverage()) {
                top = student;
            }
        }
        return top;
    }

    public Student getLowestStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student lowest = students.get(0);
        for (Student student : students) {
            if (student.getAverage() < lowest.getAverage()) {
                lowest = student;
            }
        }
        return lowest;
    }

    public long getPassedCount() {
        return students.stream().filter(Student::isPassed).count();
    }

    public long getFailedCount() {
        return students.stream().filter(student -> !student.isPassed()).count();
    }
}

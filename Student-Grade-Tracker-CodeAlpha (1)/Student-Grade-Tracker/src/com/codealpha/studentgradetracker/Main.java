package com.codealpha.studentgradetracker;

import java.util.List;
import java.util.Scanner;

/**
 * Main class containing the console user interface.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String[] SUBJECTS = {
            "Java", "DBMS", "Operating System", "Computer Networks", "Software Engineering"
    };

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();

        printWelcome();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent(tracker);
                case 2 -> viewAllStudents(tracker);
                case 3 -> searchStudent(tracker);
                case 4 -> updateStudentMarks(tracker);
                case 5 -> deleteStudent(tracker);
                case 6 -> printSummaryReport(tracker);
                case 7 -> {
                    System.out.println("\nThank you for using Student Grade Tracker!");
                    running = false;
                }
                default -> System.out.println("\nInvalid choice. Please select 1-7.");
            }
        }

        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("\n========================================");
        System.out.println("       STUDENT GRADE TRACKER");
        System.out.println("       CodeAlpha Internship Task 1");
        System.out.println("========================================");
    }

    private static void printMenu() {
        System.out.println("\n--------------- MENU ------------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student Marks");
        System.out.println("5. Delete Student");
        System.out.println("6. Class Summary Report");
        System.out.println("7. Exit");
        System.out.println("----------------------------------------");
    }

    private static void addStudent(GradeTracker tracker) {
        System.out.println("\n========== ADD STUDENT ==========");

        int rollNumber = readPositiveInt("Enter roll number: ");

        if (tracker.findByRollNumber(rollNumber) != null) {
            System.out.println("A student with this roll number already exists.");
            return;
        }

        String name = readNonEmptyString("Enter student name: ");
        double[] marks = readMarks();

        Student student = new Student(rollNumber, name, marks);
        tracker.addStudent(student);

        System.out.println("\nStudent added successfully!");
        printStudentDetails(student);
    }

    private static double[] readMarks() {
        double[] marks = new double[SUBJECTS.length];

        System.out.println("\nEnter marks out of 100:");

        for (int i = 0; i < SUBJECTS.length; i++) {
            marks[i] = readMark(SUBJECTS[i] + ": ");
        }

        return marks;
    }

    private static void viewAllStudents(GradeTracker tracker) {
        System.out.println("\n========== ALL STUDENTS ==========");

        List<Student> students = tracker.getStudents();

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        printTableHeader();

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("----------------------------------------");
        System.out.println("Total Students: " + students.size());
    }

    private static void searchStudent(GradeTracker tracker) {
        System.out.println("\n========== SEARCH STUDENT ==========");

        int rollNumber = readPositiveInt("Enter roll number: ");
        Student student = tracker.findByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        printStudentDetails(student);
    }

    private static void updateStudentMarks(GradeTracker tracker) {
        System.out.println("\n========== UPDATE MARKS ==========");

        int rollNumber = readPositiveInt("Enter roll number: ");
        Student student = tracker.findByRollNumber(rollNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + student.getName());
        double[] newMarks = readMarks();
        student.updateMarks(newMarks);

        System.out.println("\nMarks updated successfully!");
        printStudentDetails(student);
    }

    private static void deleteStudent(GradeTracker tracker) {
        System.out.println("\n========== DELETE STUDENT ==========");

        int rollNumber = readPositiveInt("Enter roll number: ");

        if (tracker.deleteStudent(rollNumber)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void printSummaryReport(GradeTracker tracker) {
        System.out.println("\n========== CLASS SUMMARY REPORT ==========");

        if (tracker.getStudentCount() == 0) {
            System.out.println("No student records available.");
            return;
        }

        Student top = tracker.getTopStudent();
        Student lowest = tracker.getLowestStudent();

        System.out.printf("Total Students   : %d%n", tracker.getStudentCount());
        System.out.printf("Passed Students   : %d%n", tracker.getPassedCount());
        System.out.printf("Failed Students   : %d%n", tracker.getFailedCount());
        System.out.printf("Class Average     : %.2f%%%n", tracker.getClassAverage());

        System.out.println("\nTop Student:");
        System.out.printf("Roll No: %d | Name: %s | Average: %.2f%% | Grade: %s%n",
                top.getRollNumber(), top.getName(), top.getAverage(), top.getGrade());

        System.out.println("\nLowest Average Student:");
        System.out.printf("Roll No: %d | Name: %s | Average: %.2f%% | Grade: %s%n",
                lowest.getRollNumber(), lowest.getName(), lowest.getAverage(), lowest.getGrade());

        System.out.println("\nSubject-wise Class Averages:");

        for (int subjectIndex = 0; subjectIndex < SUBJECTS.length; subjectIndex++) {
            double total = 0;

            for (Student student : tracker.getStudents()) {
                total += student.getMarks()[subjectIndex];
            }

            double average = total / tracker.getStudentCount();

            System.out.printf("%-22s : %.2f%%%n", SUBJECTS[subjectIndex], average);
        }
    }

    private static void printStudentDetails(Student student) {
        System.out.println("\n----------------------------------------");
        System.out.println("Roll Number : " + student.getRollNumber());
        System.out.println("Name        : " + student.getName());

        double[] marks = student.getMarks();

        System.out.println("\nSubject Marks:");
        for (int i = 0; i < SUBJECTS.length; i++) {
            System.out.printf("%-22s : %.2f%n", SUBJECTS[i], marks[i]);
        }

        System.out.println("----------------------------------------");
        System.out.printf("Total       : %.2f / 500%n", student.getTotal());
        System.out.printf("Average     : %.2f%%%n", student.getAverage());
        System.out.println("Grade       : " + student.getGrade());
        System.out.println("Result      : " + (student.isPassed() ? "PASS" : "FAIL"));
        System.out.printf("Highest Mark: %.2f%n", student.getHighestScore());
        System.out.printf("Lowest Mark : %.2f%n", student.getLowestScore());
        System.out.println("----------------------------------------");
    }

    private static void printTableHeader() {
        System.out.printf("%-8s %-22s %-10s %-10s %-8s %-8s%n",
                "Roll", "Name", "Total", "Average", "Grade", "Result");
        System.out.println("------------------------------------------------------------------");
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);

            if (value > 0) {
                return value;
            }

            System.out.println("Please enter a positive number.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double readMark(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                double mark = Double.parseDouble(scanner.nextLine().trim());

                if (mark >= 0 && mark <= 100) {
                    return mark;
                }

                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Name cannot be empty.");
        }
    }
}

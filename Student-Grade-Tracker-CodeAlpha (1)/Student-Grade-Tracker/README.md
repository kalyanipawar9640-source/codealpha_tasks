# Student Grade Tracker 🎓

A professional **Java console application** for managing student grades.  
This project is suitable for the **CodeAlpha Java Programming Internship – Task 1: Student Grade Tracker**.

## ✨ Features

- Add student details and subject marks
- Store students using `ArrayList`
- Store subject marks using arrays
- Automatically calculate:
  - Total marks
  - Average percentage
  - Grade
  - Highest score
  - Lowest score
- View all students
- Search student by roll number
- Update student marks
- Delete a student
- Generate a complete class summary report
- Input validation for invalid marks and duplicate roll numbers
- Clean menu-driven console interface

## 🛠️ Technologies

- Java
- Object-Oriented Programming
- ArrayList
- Arrays
- Loops and conditional statements
- Methods
- Exception handling

## 📁 Project Structure

```text
Student-Grade-Tracker/
├── src/
│   └── com/
│       └── codealpha/
│           └── studentgradetracker/
│               ├── Main.java
│               ├── Student.java
│               └── GradeTracker.java
├── .gitignore
├── LICENSE
└── README.md
```

## ▶️ How to Run

### Option 1: Command Prompt / PowerShell

Open the project folder:

```bash
cd Student-Grade-Tracker
```

Compile:

```bash
javac -d out src/com/codealpha/studentgradetracker/*.java
```

Run:

```bash
java -cp out com.codealpha.studentgradetracker.Main
```

### Option 2: IntelliJ IDEA / Eclipse

1. Import/open the project.
2. Mark `src` as the source folder if required.
3. Run `Main.java`.

## 📊 Grade Scale

| Percentage | Grade |
|---|---|
| 90–100 | A+ |
| 80–89 | A |
| 70–79 | B |
| 60–69 | C |
| 50–59 | D |
| 40–49 | E |
| Below 40 | F |

## 💻 Sample Menu

```text
========================================
       STUDENT GRADE TRACKER
========================================
1. Add Student
2. View All Students
3. Search Student
4. Update Student Marks
5. Delete Student
6. Class Summary Report
7. Exit
========================================
Enter your choice:
```

## 🎯 Internship Requirement Mapping

- **Input and manage student grades:** Add, update, search and delete operations
- **Calculate average, highest and lowest scores:** Implemented in `Student` and `GradeTracker`
- **Use arrays or ArrayLists:** `ArrayList<Student>` and `double[] marks`
- **Display summary report:** Class Summary Report option
- **Console-based interface:** Menu-driven console application

## 👩‍💻 Author

**Kalyani Pawar**

Built as a Java internship project for CodeAlpha.

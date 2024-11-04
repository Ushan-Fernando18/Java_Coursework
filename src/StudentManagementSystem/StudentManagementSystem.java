package StudentManagementSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.io.IOException;

public class StudentManagementSystem {
    static int ALL_STUDENTS = 250;           //Declare maximum number of students that can register in a semester
    static ArrayList<Student> students = new ArrayList<>();   //ArrayList named students of type student
                                                              //Creates a collection to store the  student objects
                                                              // and initializes an empty list called ArrayList
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;                      //Ask the user to enter an option from the main menu

        do {
            System.out.println("\n-- Student Activity Management System --");
            System.out.println("1. Check available seats");
            System.out.println("2. Register a new student");
            System.out.println("3. Delete a student");
            System.out.println("4. Find a student");
            System.out.println("5. Store student details to a file");
            System.out.println("6. Load student details from the file");
            System.out.println("7. View the list of students based on their names");
            System.out.println("8. Additional controls");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");

             //While loop to repeat until user enters the right option
            while (!scanner.hasNextInt()) {         //do- while loop to repeat until user enters the right option.
                                                    //Checks if the user input is not an integer
                System.out.println("Invalid choice. Please enter a number between 1-9");  //Displays a message if user                                                                          enters anything other than 1-9
                System.out.print("Enter your choice (1-9): ");
                scanner.next();         //Clear the wrong input user entered
            }

            choice = scanner.nextInt(); //Reads the user choice as an integer and store it in the choice variable
             scanner.nextLine();

            switch (choice) {       //switch statement to check the user input from menu and calls the dedicated method
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    findStudent(scanner);
                    break;
                case 5:
                    storeStudentDetails();
                    break;
                case 6:
                    loadStudentDetails();
                    break;
                case 7:
                    viewStudents();
                    break;
                case 8:
                    additionalControls(scanner);
                    break;
                case 9:
                    System.out.println("Exiting from the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    //If the choice is an invalid choice it prints an error message
            }
        } while (choice != 9);      // repeating the loop until the user decides to exit the program
    }

    //Calculates and will display the available seats for registering
    public static void checkAvailableSeats() {
        int availableSeats =ALL_STUDENTS - students.size();
        System.out.println("Available seats: " + availableSeats);
        System.out.println("Number of students registered until now: " + students.size());
    }
    //Method to register a new student
    public static void registerStudent(Scanner scanner) {
        while (true) {
            if (students.size() >= ALL_STUDENTS) {   // Checks if seats are full and if it is full will display a message and exits the loop
                System.out.println("Sorry! All seats are full! Unable to register a new student");
                return;
            }

            System.out.print("Enter student ID (6 digits): ");
            String id = scanner.nextLine();     // Read everything that user types and assign to the id variable.

            if (!isValidStudentID(id)) {        // Checking if the ID is invalid
                System.out.println("Invalid student ID. Enter the correct ID which has only 6 digits.");
                System.out.print("Enter 1 to try again/Enter 0 to go back: ");
                String choice = scanner.nextLine();
                if (!choice.equals("1")) {
                    return; // Exit to main menu if user enters anything other than 1
                }
            } else if (isDuplicateStudentID(id)) {  // Checking if the ID is a duplicate
                System.out.println("Duplicate student ID. Please enter a unique student ID.");
                System.out.print("Enter 1 to try again/Enter 0 to go back: ");
                String choice = scanner.nextLine();
                if (!choice.equals("1")) {
                    return; // Exit to main menu if user enters anything other than 1
                }
            } else {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                Student student = new Student(id, name);   // Creates a new Student object

                students.add(student);           // Add the new student object containing the student info to the students list
                System.out.println("New student registered successfully!");
                return;         // Exit to main menu after registering
            }
        }
    }

    static boolean isDuplicateStudentID(String id) {     //Checking if the entered student ID already exists
        for (Student student : students) {
            if (student.studentID.equals(id)) {
                return true;  //If there is a duplicate ID found in system
            }
        }
        return false;  //If no duplicate found
    }

    static boolean isValidStudentID(String id) {    //Checking if the student ID length is exactly 6 digits
        if (id.length() != 6) {
            return false;
        }
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

        //Method to delete a student from the list using the ID
    public static void deleteStudent(Scanner scanner) {
        while (true) {  //Loop until user enters a valid existing student ID to delete
            System.out.print("Please enter the student ID which you want to delete (enter 0 to go back): ");
            String id = scanner.nextLine();

            if (id.equals("0")) {
                System.out.println("Returning to the main menu....");
                return;     //Exit if user wants to go back to main menu
            }

            boolean found = false;  //Declares a variable named found and initializes it to false
            for (Student student : students) {  //Loop to iterate over each Student object in the students list.
                if (student.studentID.equals(id)) {
                    students.remove(student); //Removes the student object from the students list using the remove method
                    System.out.println("Selected student has been deleted successfully!");
                    found = true;
                    break;      //going back to main menu after deleting the student details
                }
            }
            if (!found) {
                System.out.println("Student ID cannot be found. Please check and try again!");
            } else {
                return; // Exit the method if the student has already deleted
            }
        }
    }

        //Method to find a student by the student ID
    public static void findStudent(Scanner scanner) {
        while (true) {      //Loop to enter the correct student ID and user can go back by entering 0
            System.out.print("Enter the student ID to find a registered student (enter 0 to go back): ");
            String id = scanner.nextLine();

            if (id.equals("0")) {
                System.out.println("Returning to the main menu...");
                return;     //Go back to main menu if user enters 0
            }
            boolean found = false;

            for (Student student : students) {
                if (student.studentID.equals(id)) {     //Checks if student ID of the current Student object in the loop                                            is equal to the user entered id
                    System.out.println("Student ID: " + student.studentID);
                    System.out.println("Student Name: " + student.studentName); //prints the student ID and name
                    for (int i = 0; i < student.modules.length; i++) { //for loop that iterates 3 modules of the student
                        System.out.println("Module " + (i + 1) + " Mark: " + student.getModuleMark(i) + ", Grade: " + student.getModuleGrade(i));   //i + 1 gives the correct module number starting from 1

                    }
                    System.out.println("Average Marks: " + student.getAverageMarks());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Invalid student ID! Please enter an existing ID!");
            } else {
                return; // Exit the method if the student has been found
            }
        }
    }

            //Method to store the entered student details into a text file
    public static void storeStudentDetails() {
        FileWriter fileWrite = null;
        try {
            fileWrite = new FileWriter("students.txt");  // Creates a FileWriter object to write data to a file named "students.txt"
            for (Student student : students) {  // for loop to iterate in Student object in the students.
                fileWrite.write(student.studentID + "," + student.studentName);  // Writes the studentID and student name separated by a comma.
                for (int i = 0; i < student.modules.length; i++) {  // For loop to iterate through modules of the current Student.
                    fileWrite.write("," + student.getModuleMark(i));  // Writes the mark of the current module to the file.
                }
                fileWrite.write("\n");  // Writes a newline character to the file.
            }
            System.out.println("Student details stored successfully in the text file.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details into the file.");
        } finally {
            if (fileWrite != null) {
                try {
                    fileWrite.close();  // Closes the FileWriter.
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the file.");
                }
            }
        }
    }

        //Method to load data from the file to the system
    public static void loadStudentDetails() {
        BufferedReader reader = null;       //Declares a variable named reader and BufferedReader type.
                                            //Initialized to null because a file haven't opened yet.
        try {
            reader = new BufferedReader(new FileReader("students.txt")); //Opens students.txt using FileReader                                                          and creates a BufferedReader object to read from it
            String line;        //Declares a variable named line to store each line of text read from the file.
            students.clear();
            while ((line = reader.readLine()) != null) {    //Loop to read each line from the file and store in the                                                     line variable.The loop runs until readLine returns null
                String[] details = line.split(",");
                if (details.length != 5) {  //Checks if the details array has exactly 5 elements (3 modules+ID and Name)
                    System.out.println("Data format error in file. Expected 5 fields, found " + details.length);
                    continue;
                }
                //This code builds a Student object with ID and name from file then loops through the modules and sets the marks from the file and it converts marks from strings to integers.
                Student student = new Student(details[0], details[1]);
                for (int i = 0; i < student.modules.length; i++) {
                    student.setModuleMark(i, Integer.parseInt(details[i + 2])); //starts from index 2 because 1 and 2 is                                                            ID and name
                }
                students.add(student);
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading student details.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();  // Closes the BufferedReader to release resources.
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the reader.");
                }
            }
        }
    }

        //Method to view the list of registered students
    public static void viewStudents() {
        String[] sortedStudentNames = new String[students.size()];  //Declares a String array with a size equal to the                                                              number of students
                                                                //Array will temporarily hold student names for sorting
        for (int i = 0; i < students.size(); i++) {
            sortedStudentNames[i] = students.get(i).studentName;
        }
        Arrays.sort(sortedStudentNames);        //Sorts the sortedStudentNames array alphabetically

        System.out.println("List of registered students: ");
        for (String name : sortedStudentNames) {        //Another for loop that iterates through each name in the                                                           sortedStudentNames array
            if (name != null) {
                for (Student student : students) {
                    if (name.equals(student.studentName)) {
                        System.out.println("Student Name: " + student.studentName + ", Student ID: " + student.studentID);
                    }       //If the names match it prints the student's name and ID from the current student object.
                }
            }
        }
    }

    public static void additionalControls(Scanner scanner) {
        int choice;     //A variable names choice to store user input
                        //do-while loop to repeat until the user enters 6
        do {
            System.out.println("Additional Information about students: ");
            System.out.println("1. Change a student's name");
            System.out.println("2. Add module marks");
            System.out.println("3. View student details");
            System.out.println("4. Generate summary report");
            System.out.println("5. Generate complete report");
            System.out.println("6. Return to main menu");
            System.out.print("Enter your choice(1-6): ");
            choice = scanner.nextInt();         //reads the user input and store in the choice variable.
            scanner.nextLine();

            switch (choice) {
                case 1:
                    changeStudentName(scanner);
                    break;
                case 2:
                    addModuleMarks(scanner);
                    break;
                case 3:
                    viewStudentDetails(scanner);
                    break;
                case 4:
                    generateSystemSummary();
                    break;
                case 5:
                    generateCompleteReport();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice!= 6);
    }

            //Method to update a student's name using student ID
    public static void changeStudentName(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.studentID.equals(id)) {         //Checks if the current studentID is equal to the entered id
                System.out.print("Enter the new student name: ");
                String name = scanner.nextLine();       //If the ID matches it prompts the user for a new name,
                student.studentName = name;             // reads it and updates the studentName property
                System.out.println("Student name updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

        //Method to add the module marks for students using ID
        public static void addModuleMarks(Scanner scanner) {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine(); // Reads the ID entered by user and store in id variable as a String.

            for (Student student : students) { // for loop that iterates through all Student objects in the students.
                if (student.studentID.equals(id)) { // If a matching ID is found, it allows user to enter marks for each module in the student modules array.
                    for (int i = 0; i < student.modules.length; i++) {
                        int marks;
                        while (true) { // Loop to ensure valid input for marks.
                            System.out.print("Enter marks for module " + (i + 1) + ": ");
                            if (!scanner.hasNextInt()) { //Checking if the input is an integer.
                                System.out.println("Invalid input. Please enter a valid mark.");
                                scanner.next(); // Clear the invalid input
                                continue;
                            }
                            marks = scanner.nextInt();
                            scanner.nextLine();

                            if (marks < 0 || marks > 100) { //Check if the marks are in the range of 0-100.
                                System.out.println("Invalid marks. Please enter a number between 1 and 100.");
                            } else {
                                break; // Exit the loop if the input is valid.
                            }
                        }
                        student.setModuleMark(i, marks); //setModuleMark method to set entered marks for the current module
                    }
                    System.out.println("Module marks updated successfully.");
                    return;
                }
            }
            System.out.println("Student ID not found.");
        }

    public static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter student ID: ");     //prompts the user to enter the ID of the student to view details
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.studentID.equals(id)) {      //If the IDs match it prints the student ID and name
                System.out.println("Student ID: " + student.studentID);
                System.out.println("Student Name: " + student.studentName);
                for (int i = 0; i < student.modules.length; i++) {    //Retrieves the marks and grade and prints it
                    System.out.println("Module " + (i + 1) + " Marks: " + student.getModuleMark(i) + ", Grade: " + student.getModuleGrade(i));
                }
                System.out.println("Average Marks: " + student.getAverageMarks()); //Prints the average mark
                return;
            }
        }
        System.out.println("Student ID not found.");
    }
        //Method to display a summary of module marks of the students
    public static void generateSystemSummary() {
        int totalRegistrations = students.size();   //initializes an integer variable with the size of the students list.                                            and will return the number of students in the list

        //initialize 3 integer variables to count the number of students who scored more than 40 marks in 3 modules.
        int studentsScoredMoreThan40M1 = 0;
        int studentsScoredMoreThan40M2 = 0;
        int studentsScoredMoreThan40M3 = 0;

        //This loop iterate through each student object in students list and checks if the mark is above 40 for each module
        // If the mark is above 40 count will increase by +1
        for (Student student : students) {
            if (student.getModuleMark(0)>40) studentsScoredMoreThan40M1++ ;
            if (student.getModuleMark(1)>40) studentsScoredMoreThan40M2++ ;
            if (student.getModuleMark(2) >40) studentsScoredMoreThan40M3++ ;
        }
        System.out.println("\n-- Summary of the Module marks --");
        System.out.println("Total student registrations: " + totalRegistrations);
        System.out.println("Total students who scored more than 40 in Module 1: " + studentsScoredMoreThan40M1);
        System.out.println("Total students who scored more than 40 in Module 2: " + studentsScoredMoreThan40M2);
        System.out.println("Total students who scored more than 40 in Module 3: " + studentsScoredMoreThan40M3);
    }

        //Method to generate a full report of the student details with module marks and average grade
    public static void generateCompleteReport() {
        // Sort students by average marks (highest to lowest) using Bubble sort
        for (int i = 0; i < students.size() - 1; i++) {     //This loop controls the number of passes through the list
            for (int j = 0; j < students.size() - 1 - i; j++) {

                //Checks if the average marks of the student at j are less than the student at position j + 1
                if (students.get(j).getAverageMarks() < students.get(j + 1).getAverageMarks()) {
                    // If true swap students at position j and j+1

                    //Temporary variable is used to hold the student at position j, Then the student at position j+1 is moved to position j and the student in temp is moved to position j + 1
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("\n--- Complete Report of Students (Sorted by Average Marks) ---");
        System.out.println("ID\t\tName\t\t\t M1\t M2\t M3\t\tTotal \tAverage\t\tGrade"); //Prints the column headers for the report

        for (Student student : students) {
            System.out.printf("%s\t%s", student.studentID, student.studentName); //Prints the student ID and name                                                                               separated by a tab character
            for (int i = 0; i < student.modules.length; i++) {  //Nested loop to iterate over the modules and prints the                                                        mark for each module separated by tab character
                System.out.printf("\t %d", student.getModuleMark(i));
            }

            //Prints the student total marks, average marks(two decimal places) and overall grade
            System.out.printf("\t\t%d\t\t%.2f\t\t%s\n", student.getTotalMarks(), student.getAverageMarks(), student.getOverallGrade());
        }
    }

}

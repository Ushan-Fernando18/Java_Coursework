package StudentManagementSystem;

public class Student {              //Initializes a Student object with a student ID, name and an array of 3 Modules.
    String studentID;
    String studentName;
    Module[] modules;

    public Student(String studentID, String studentName) {
        this.studentID =studentID;
        this.studentName =studentName;
        this.modules = new Module[3];   //This creates an array of 3 Module objects when a Student object is created
        for (int i =0; i<modules.length; i++) {
            modules[i] =new Module();
        }
    }

    public int getModuleMark(int moduleIndex) {     // Extract the mark of a specific module by
                                                    // calling the getMark method of the Module class.
        return modules[moduleIndex].getMark();
    }

    public void setModuleMark(int moduleIndex,int mark) {      //Sets the mark of a specific module by calling
                                                                // the setMark method
        modules[moduleIndex].setMark(mark);
    }

    public String getModuleGrade(int moduleIndex) {     // Extract the grade of a specific module by calling the getGrade method
        return modules[moduleIndex].getGrade();
    }

    public int getTotalMarks() {        //This method calculates the total marks
                                        // by summing up the marks of 3 Module objects in the modules array.
        int total =0;
        for (Module module : modules) {
            total += module.getMark();
        }
        return total;
    }

    public double getAverageMarks() {       //Returns the average of 3 module marks
        return getTotalMarks()/3.0;
    }

    public String getOverallGrade() {           //Returns the overall grade based on the average mark.
        double average = getAverageMarks();
        if (average>=80) return "Distinction";
        else if (average>=70) return "Merit";
        else if (average>=40) return "Pass";
        else return "Fail";
    }
}

package StudentManagementSystem;

public class Module {
    int mark;

    public Module() {       //Initializes a Module object with a default mark of 0.
        this.mark = 0;
    }

    public void setMark(int mark) {     //Sets the mark for the module
        this.mark = mark;       //Assigns the provided mark value to the mark variable of the current Module object.
    }

    public int getMark() {      //Returns the current mark of the module
        return mark;
    }

    public String getGrade() {      //Checks and returns the grade of student according to module mark
        if (mark >= 80) {
            return "Distinction";
        } else if (mark >= 70) {
            return "Merit";
        } else if (mark >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}


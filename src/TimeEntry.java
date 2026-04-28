import java.time.LocalDate;
import java.util.Scanner;

public class TimeEntry {
    private LocalDate timeEntryDate;
    private Double hoursWorked;

    //constructor
    public TimeEntry(LocalDate newTimeEntryDate, Double newHoursEntered) {
        timeEntryDate = newTimeEntryDate;
        hoursWorked = newHoursEntered;
    }

    //setter functions
    public void setHoursWorked(Double newHoursWorked) {
        hoursWorked = newHoursWorked;
    }

    //output functions
    public void printTimeRecord() {
        System.out.println("Date: " + timeEntryDate);
        System.out.println("Hours Worked: " + hoursWorked);
    }



}
    

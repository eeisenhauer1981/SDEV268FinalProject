import java.time.LocalDate;

public class TimeEntry {
    private LocalDate workDate;
    private int dailyHours;

    public TimeEntry(LocalDate date, int hours) {
        workDate = date;
        dailyHours = hours;
    }

    //setters for editing
    public void setWorkDate(LocalDate newDate) {
        workDate = newDate;
    }

    public void setDailyHours(int newHours) {
        dailyHours = newHours;
    }
    
}

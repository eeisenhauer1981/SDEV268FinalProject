import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

class Dates {
    //date of end of last pay period (previous Friday)
    private LocalDate lastPayPeriod;
    //date of next pay check (one week from upcoming Friday)
    private LocalDate currentPayDate;    

    //constructor sets dates at program start
    public Dates() {
        setLastPayPeriod();
        setCurrentPayDate();
    }

    //setter functions
    public void setLastPayPeriod() {
        lastPayPeriod = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
    }

    public void setCurrentPayDate() {
        currentPayDate = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }

    //returns current pay date for processing paychecks
    public LocalDate getCurrentPayDate() {
        setCurrentPayDate();
        return currentPayDate;
    }

    //checks to make sure time entry date is within current pay period
    public boolean isValidTimeEntryDate(LocalDate dateToEnter){
        setLastPayPeriod();

        if((dateToEnter.isBefore(LocalDate.now()) || dateToEnter.isEqual(LocalDate.now())) && dateToEnter.isAfter(lastPayPeriod)) {
            return true;
        }
        else{
            return false;
        }
    }
}

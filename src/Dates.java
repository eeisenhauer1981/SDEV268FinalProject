import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Dates {
    //date of end of last pay period (previous Friday)
    private LocalDate lastPayPeriod;
    //date of end of current pay period (upcoming Friday)
    private LocalDate currentPayPeriod;
    //date of next pay check (one week from upcoming Friday)
    private LocalDate currentPayDate;
    //pay period
    private ArrayList<LocalDate> payPeriod = new ArrayList<>();

    //constructor sets dates at program start
    public Dates(){
        setLastPayPeriod();
        setCurrentPayPeriod();
        setCurrentPayDate();
    }

    //setter functions
    public void setLastPayPeriod() {
        lastPayPeriod = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
    }

    public void setCurrentPayPeriod() {
        currentPayPeriod = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }

    public void setCurrentPayDate() {
        currentPayDate = currentPayPeriod.plusWeeks(1);
    }

    public void setPayPeriod(LocalDate baseDate) {
        LocalDate startOfPeriod = baseDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
        for(int i = 0; i < 7; i++) {
            payPeriod.add(i, startOfPeriod);
            startOfPeriod = startOfPeriod.plusDays(1);
        }
    }

    //getter functions
    public ArrayList<LocalDate> getPayPeriod() {
        return payPeriod;
    }

    //date validation functions
    public boolean isValidTimeEntryDate(LocalDate dateToEnter){
        setLastPayPeriod();

        if((dateToEnter.isBefore(LocalDate.now()) || dateToEnter.isEqual(LocalDate.now())) && dateToEnter.isAfter(lastPayPeriod)) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidRunPayrollDate(LocalDate runDate){
        setCurrentPayPeriod();
        setCurrentPayDate();

        if((runDate.isBefore(currentPayDate) || runDate.isEqual(currentPayDate)) && runDate.isAfter(currentPayPeriod)) {
            return true;
        }
        else{
            return false;
        }
    }
}

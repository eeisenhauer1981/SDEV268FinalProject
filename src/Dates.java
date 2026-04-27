import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Dates {
    //date of end of last pay period (previous Friday)
    private LocalDate lastPayPeriod;
    //date of end of current pay period (upcoming Friday)
    private LocalDate currentPayPeriod;
    //date of next pay check (one week from upcoming Friday)
    private LocalDate currentPayDate;

    public Dates(){
        setLastPayPeriod();
        setCurrentPayPeriod();
        setCurrentPayDate();
    }

    public void setLastPayPeriod() {
        lastPayPeriod = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        System.out.println(lastPayPeriod);
    }

    public void setCurrentPayPeriod() {
        currentPayPeriod = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(currentPayPeriod);
    }

    public void setCurrentPayDate() {
        currentPayDate = currentPayPeriod.plusWeeks(1);
        System.out.println(currentPayDate);
    }
}

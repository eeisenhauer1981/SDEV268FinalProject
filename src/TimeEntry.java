import java.util.Scanner;

public class TimeEntry {

    //temp for testing
    public static double tempHoursWorked() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many hours? ");
        double hoursWorked = scanner.nextDouble();
        
        scanner.close();

        return hoursWorked;

    }
    
}

import java.util.Scanner;

public class PTOEntry {

    //temp for testing
    public static int tempPTO() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many PTO days? ");
        int PTODays = scanner.nextInt();
        
        scanner.close();

        return PTODays;

    }
    
}
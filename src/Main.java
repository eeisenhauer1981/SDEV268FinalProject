import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("department: ");
        String department = scanner.nextLine();
        System.out.print("jobTitle: ");
        String jobTitle = scanner.nextLine();
        System.out.print("lastName: ");
        String lastName = scanner.nextLine();
        System.out.print("firstName: ");
        String firstName = scanner.nextLine();
        System.out.print("middleName: ");
        String middleName = scanner.nextLine();
        System.out.print("suffix: ");
        String suffix = scanner.nextLine();
        System.out.print("active: ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("birthMonth: ");
        int birthMonth = scanner.nextInt();
        scanner.nextLine();
        System.out.print("birthDate: ");
        int birthDate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("birthYear: ");
        int birthYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("gender: ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("payType: ");
        String payType = scanner.nextLine();
        System.out.print("address1: ");
        String address1 = scanner.nextLine();
        System.out.print("address2: ");
        String address2 = scanner.nextLine();
        System.out.print("city: ");
        String city = scanner.nextLine();
        System.out.print("state: ");
        String state = scanner.nextLine();
        System.out.print("zip: ");
        String zip = scanner.nextLine();
        System.out.print("hireMonth: ");
        int hireMonth = scanner.nextInt();
        scanner.nextLine();
        System.out.print("hireDate: ");
        int hireDate = scanner.nextInt();
        scanner.nextLine();
        System.out.print("hireYear: ");
        int hireYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("medicalCoverageType: ");
        String medicalCoverageType = scanner.nextLine();
        System.out.print("dependents: ");
        int dependents = scanner.nextInt();
        scanner.nextLine();
        System.out.print("basePay: ");
        double basePay = scanner.nextDouble();
        scanner.nextLine();

        
        scanner.close();
    }   
    
}

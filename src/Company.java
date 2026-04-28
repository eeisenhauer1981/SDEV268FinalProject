import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;


public class Company {
    String name;
    int checkNumber;
    int employeeNumber;
    boolean payrollProcessing;
    HashMap<Integer, Employee> employees = new HashMap<>();
    HashMap<Integer, PayCheck> paychecks = new HashMap<>();

    //Constructors
    public Company(){
        this.name = "New Company";
        this.checkNumber = -1;
        this.employeeNumber = -1;
        this.payrollProcessing = false;
    }

    public Company(String newName){
        this.name = newName;
        this.checkNumber = 1;
        this.employeeNumber = 1;
        this.payrollProcessing = false;
    }

    //setters
    public int increaseCheckNumber() {
        return checkNumber + 1;
    }

    //doer functions

    //input new employee information
    public void loadEmployeeData(Scanner scanner /*temp scanner until GUI*/){

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Middle Name:");
        String middleName = scanner.nextLine();
        
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        System.out.println("Suffix:");
        String suffix = scanner.nextLine();

        System.out.println("Department:");
        String department = scanner.nextLine();
        //MGMT, DIS, PRD, HR

        System.out.println("Job Title:");
        String jobTitle = scanner.nextLine();

        Boolean active = true;

        System.out.println("Hire Date (enter year, enter month, enter day):");
        int hireYear = scanner.nextInt();
        scanner.nextLine();
        int hireMonth = scanner.nextInt();
        scanner.nextLine();
        int hireDay = scanner.nextInt();
        scanner.nextLine();
        LocalDate hireDate = LocalDate.of(hireYear, hireMonth, hireDay);

        System.out.println("Pay Type:");
        String payType = scanner.nextLine();
        //"Hourly", "Salary"

        System.out.println("Base Pay:");
        Double basePay = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Date of Birth:");
        int birthYear = scanner.nextInt();
        scanner.nextLine();
        int birthMonth = scanner.nextInt();
        scanner.nextLine();
        int birthDay = scanner.nextInt();
        scanner.nextLine();
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        
        System.out.println("Gender:");
        String gender = scanner.nextLine();
        //"Female", "Male", "Non-Binary", "Not Reported"
        
        System.out.println("Address Line 1:");
        String address1 = scanner.nextLine();

        System.out.println("Address Line 2:");
        String address2 = scanner.nextLine();

        System.out.println("City:");
        String city = scanner.nextLine();

        System.out.println("State:");
        String state = scanner.nextLine();

        System.out.println("Zip Code:");
        String zip = scanner.nextLine();

        System.out.println("Number of Dependents:");
        int dependents = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Medical Coverage Type:");
        String medicalCoverage = scanner.nextLine();
        //"Family", "Single"

        //create Employee object
        Employee newEmployee = new Employee (
                employeeNumber,
                firstName,
                middleName,
                lastName,
                suffix,
                department,
                jobTitle,
                active,
                hireDate,
                payType,
                basePay,
                birthDate,
                gender,
                address1,
                address2,
                city,
                state,
                zip,
                dependents,
                medicalCoverage
            );
        //call function to add newEmployee to company's employee HashMap
        addEmployee(employeeNumber, newEmployee);
    }
    
    //add employee to company's employee HashMap
    public void addEmployee(int currEmployeeNumber, Employee newEmployee) {
        employees.put(currEmployeeNumber, newEmployee);
        //increase employeeNumber by 1 to assign next employee ID
        employeeNumber = employeeNumber + 1;
    }

    public void editEmployeeData(Scanner scanner /*temp scanner until GUI*/) {
        //for testing, can only edit last name, pay type, dependents, medical coverage, and base pay
        Employee editEmployee = employeeSearch(scanner);

        System.out.println("You are editing:");
        editEmployee.printEmployeeInfo();

        System.out.println("Which field would you like to change? Enter quit if you are done editing.");
        String editData = scanner.nextLine();        

        while(!editData.equals("quit")){
            System.out.println("Enter new " + editData);
            String newData = scanner.nextLine();

            if(editData.equals("last name")){
               editEmployee.setLastName(newData);
            }
            else if(editData.equals("pay type")){
               editEmployee.setPayType(newData);
            }
            else if(editData.equals("dependents")){
                int newDependents = Integer.parseInt(newData);
                editEmployee.setDependents(newDependents);
            }
            else if(editData.equals("medical coverage")){
               editEmployee.setMedicalCoverageType(newData);
            }
            else if(editData.equals("base pay")){
                Double newPay = Double.parseDouble(newData);
                editEmployee.setBasePay(newPay);
            }
            else {
                System.out.println("Invalid entry. Try again.");
            }

            System.out.println("If you would like, enter another field to edit. Or enter quit to finish.");
            editData = scanner.nextLine();
        }
        
    }
    
    public void editTimeClock(Scanner scanner, Dates dates) {
        Employee editEmployee = employeeSearch(scanner);
        editEmployee.editTimePunch(scanner, dates);
    }

    public Employee employeeSearch(Scanner scanner /*temp scanner until GUI*/) {
        System.out.println("Enter employee ID");
        int searchID = scanner.nextInt();
        scanner.nextLine();
        return employees.get(searchID);
    }

    //output functions
    //loops through employees and outputs each employee's information
    public void printEmployees() {
        for(int i : employees.keySet()) {
            employees.get(i).printEmployeeInfo();
        }
    }

}

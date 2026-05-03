import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collection;

class Company {
    String name;
    int checkNumber;
    int employeeCount;
    boolean payrollProcessing;
    HashMap<Integer, Employee> employees = new HashMap<>();
    HashMap<Integer, PayCheck> paychecks = new HashMap<>();

    //Constructors
    public Company(){
        this.name = "New Company";
        this.checkNumber = -1;
        this.employeeCount = -1;
        this.payrollProcessing = false;
    }

    public Company(String newName){
        this.name = newName;
        this.checkNumber = 1;
        this.employeeCount = 0;
        this.payrollProcessing = false;
    }

    //setters
    public void increaseCheckNumber() {
        checkNumber = checkNumber + 1;
    }

    public void increaseEmployeeCount() {
        employeeCount = employeeCount +1;
    }

    
    //getters
    public String getName() {
        return name;
    }

    public int getCheckNumber() {return checkNumber;}

    public int getEmployeeCount() {return employeeCount;}

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    //doer functions
    public String createEmailAddress(String firstName, String lastName, int employeeID) {
        return firstName + lastName + employeeID + "@" + getName().replaceAll("\\s", "") + ".com";
    }

    //input new employee information
    public void createNewEmployee(
        String newFirstName,
        String newMiddleName,
        String newLastName,
        String newSuffix,
        String newDepartment,
        String newJobTitle,
        boolean newActive,
        LocalDate newHireDate,
        String newPayType,
        double newBasePay,
        LocalDate newBirthDate,
        String newGender,
        String newAddress1,
        String newAddress2,
        String newCity,
        String newState,
        String newZip,
        int newDependents,
        String newMedicalCoverage){
        
            increaseEmployeeCount();
            String newEmailAddress = createEmailAddress(newFirstName, newLastName, getEmployeeCount());

            //create Employee object
            Employee newEmployee = new Employee (
                this,
                getEmployeeCount(),
                newFirstName,
                newMiddleName,
                newLastName,
                newSuffix,
                newDepartment,
                newJobTitle,
                newActive,
                newHireDate,
                newPayType,
                newBasePay,
                newBirthDate,
                newGender,
                newAddress1,
                newAddress2,
                newCity,
                newState,
                newZip,
                newDependents,
                newMedicalCoverage,
                newEmailAddress
            );
        //call function to add newEmployee to company's employee HashMap
        addEmployee(getEmployeeCount(), newEmployee);
    }
    
    //add employee to company's employee HashMap
    public void addEmployee(int currEmployeeNumber, Employee newEmployee) {
        employees.put(currEmployeeNumber, newEmployee);
    }

    public void editEmployeeData(Scanner scanner /*temp scanner until GUI*/) {
        //for testing, can only edit last name, pay type, dependents, medical coverage, and base pay
        Employee editEmployee = employeeSearch(scanner);

        System.out.println("You are editing:");
        editEmployee.getEmployeeInfo();

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
    

}

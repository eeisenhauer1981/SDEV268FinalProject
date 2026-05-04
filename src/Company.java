import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collection;

class Company {
    String name;
    int checkNumber;
    int employeeCount;
    boolean payrollProcessing;
    User activeUser;
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

    public void setActiveUser(User authenticatedUser) {
        activeUser = authenticatedUser;
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

    public void editTimeClock(Scanner scanner, Dates dates) {
        Employee editEmployee = employeeSearch(scanner);
        editEmployee.editTimePunch(scanner, dates);
    }

    public Employee employeeSearch(int searchID) {
        return employees.get(searchID);
    }

    //output functions
    //loops through employees and outputs each employee's information
    

}

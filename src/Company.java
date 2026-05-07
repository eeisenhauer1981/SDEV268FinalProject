import java.time.LocalDate;
import java.util.HashMap;
import java.util.Collection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Company {
    String name;
    int checkNumber;
    int employeeCount;
    boolean payrollProcessing;
    User activeUser;
    Dates dates;
    HashMap<Integer, Employee> employees = new HashMap<>();
    HashMap<Integer, PayCheck> inProcessChecks = new HashMap<>();
    HashMap<Integer, PayCheck> paychecks = new HashMap<>();

    //Constructor
    public Company(String newName, Dates newDates){
        this.name = newName;
        this.checkNumber = 1;
        this.employeeCount = 0;
        this.payrollProcessing = false;
        this.dates = newDates;
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

    public Collection<PayCheck> getinProcessChecks() {
        return inProcessChecks.values();
    }

    //doer functions
    public String createEmailAddress(String firstName, String lastName, int employeeID) {
        return firstName + lastName + employeeID + "@" + getName().replaceAll("\\s", "") + ".com";
    }

    //add new employee 
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
    
    public void addEmployee(int currEmployeeNumber, Employee newEmployee) {
        employees.put(currEmployeeNumber, newEmployee);
    }

    public Employee employeeSearch(int searchID) {
        return employees.get(searchID);
    }

    //process payroll
    public void processPayroll() {
        for(int i : employees.keySet()) {
            if(!employees.get(i).getActive()) {
                continue;
            }
            else {
                PayCheck newPayCheck = calculatePayCheck(employees.get(i));
                addPayCheckInProcess(newPayCheck.getCheckNumber(), newPayCheck);
                increaseCheckNumber();
            }
        }

    }

    public PayCheck calculatePayCheck(Employee payEmployee) {
        return new PayCheck(payEmployee, this, dates);
    }

    public void addPayCheckInProcess(int checkNumber, PayCheck newPaycheck){
        inProcessChecks.put(checkNumber, newPaycheck);
    }

    public void addApprovedPayChecks() {
        printerPayCheckFile();
        paychecks.putAll(inProcessChecks);
        inProcessChecks.clear();
    }

    public void printerPayCheckFile() {
        String fileName = name+ "_" + LocalDate.now() + "_Paychecks.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (PayCheck payCheck : inProcessChecks.values()) {
                writer.write(payCheck.savePaycheckInfo());
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Collection;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//stores company information, employees and paychecks
//functions for adding employees, maintaining employee information, and processing payroll
class Company {
    String name;
    int employeeCount;  //stores current employee count; increases by 1 when new employee is initiated to assign next employee ID
    boolean payrollProcessing;  //true if payroll is in process to prevent time edits
    User activeUser;    //logged in user; used to validate access to employee information
    Dates dates;
    HashMap<Integer, Employee> employees = new HashMap<>();
    HashMap<Integer, PayCheck> inProcessChecks = new HashMap<>();   //temporary map for new PayChecks before they are approved
    HashMap<Integer, PayCheck> paychecks = new HashMap<>(); //approved paycheck records

    //Constructor
    public Company(String newName, Dates newDates){
        this.name = newName;
        this.employeeCount = 0;
        this.payrollProcessing = false;
        this.dates = newDates;
    }

    //setters

    public void increaseEmployeeCount() {employeeCount = employeeCount +1;}

    public void setActiveUser(User authenticatedUser) {activeUser = authenticatedUser;}

    public void setPayrollProcessing(Boolean processingStatus) {payrollProcessing = processingStatus;}

    //getters
    public String getName() {return name;}

    public int getEmployeeCount() {return employeeCount;}

    public Collection<Employee> getEmployees() {return employees.values();}

    public Collection<PayCheck> getinProcessChecks() {return inProcessChecks.values();}

    public boolean getPayrollProcessing() {return payrollProcessing;}

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
        
            //increases current employee count by 1 to include new employee and assign employee ID
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
        //add newEmployee to company's employees HashMap
        addEmployee(getEmployeeCount(), newEmployee);
    }
    
    public void addEmployee(int currEmployeeNumber, Employee newEmployee) {employees.put(currEmployeeNumber, newEmployee);}

    public Employee employeeSearch(int searchID) {return employees.get(searchID);}

    //process payroll
    public void processPayroll() {
        setPayrollProcessing(true);
        for(int i : employees.keySet()) {
            if(!employees.get(i).getActive()) {
                continue;
            }
            else {
                PayCheck newPayCheck = calculatePayCheck(employees.get(i));
                addPayCheckInProcess(newPayCheck.getPayID(), newPayCheck);
            }
        }
        setPayrollProcessing(false);

    }

    //creates a new paycheck
    public PayCheck calculatePayCheck(Employee payEmployee) {return new PayCheck(payEmployee, this, dates);}

    //adds new paycheck to temporary paycheck map
    public void addPayCheckInProcess(int checkNumber, PayCheck newPaycheck){inProcessChecks.put(checkNumber, newPaycheck);}

    //creates file of approved paychecks and adds approved paychecks to permanent paycheck map
    public void addApprovedPayChecks() {
        printerPayCheckFile();
        paychecks.putAll(inProcessChecks);
        inProcessChecks.clear();
    }

    //creates paycheck file
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


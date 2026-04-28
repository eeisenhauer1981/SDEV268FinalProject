import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class Employee {
    private int employeeID;
    private String firstName;
    private String middleName;
    private String lastName; 
    private String suffix;
    private String department;
    private String jobTitle;    
    private boolean active;
    private LocalDate hireDate;
    private String payType;
    private double basePay;
    private LocalDate dateOfBirth;
    private String gender;    
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private int dependents;
    private String medicalCoverageType;
    private HashMap<LocalDate, TimeEntry> timeClock = new HashMap<>();
    private HashMap<LocalDate, PTOEntry> PTOList = new HashMap();
        
    //default constructor
    public Employee() {
        this.employeeID = 0;
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";        
        this.suffix = "";
        this.department = "";
        this.jobTitle = "";        
        this.active = false;
        this.hireDate = null;
        this.payType = "";
        this.basePay = 0.0;
        this.dateOfBirth = null;
        this.gender = "'-'";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zip = "";
        this.dependents = 0;
        this.medicalCoverageType = "";       
    }

    //constructor with parameters
    public Employee(
        int employeeCount,
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
        String newMedicalCoverage
    ) {
            this.employeeID = employeeCount;
            this.firstName = newFirstName;
            this.middleName = newMiddleName;
            this.lastName = newLastName;
            this.suffix = newSuffix;
            this.department = newDepartment;
            this.jobTitle = newJobTitle;
            this.active = newActive;
            this.hireDate = newHireDate;
            this.payType = newPayType;
            this.basePay = newBasePay;
            this.dateOfBirth = newBirthDate;
            this.gender = newGender;
            this.address1 = newAddress1;
            this.address2 = newAddress2;
            this.city = newCity;
            this.state = newState;
            this.zip = newZip;
            this.dependents = newDependents;
            this.medicalCoverageType = newMedicalCoverage;       
    }

    //setter functions
    public void setFirstName(String firstName){this.firstName = firstName;}

    public void setMiddleName(String middleName) {this.middleName = middleName;}

    public void setLastName(String lastName){this.lastName = lastName;}

    public void setSuffix(String suffix) {this.suffix = suffix;}

    public void setDepartment(String department) {this.department = department;}

    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}

    public void setActive(boolean active) {this.active = active;}

    public void setHiredate(LocalDate hireDate) {this.hireDate = hireDate;}

    public void setPayType(String payType){this.payType = payType;}

    public void setBasePay(double basePay) {this.basePay = basePay;}

    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public void setGender(String gender){this.gender = gender;}

    public void setAddress1(String address1){this.address1 = address1;}

    public void setAddress2(String address2){this.address2 = address2;}

    public void setCity(String city) {this.city = city;}

    public void setState(String state) {this.state = state;}

    public void setZip(String zip){this.zip = zip;}

    public void setDependents(int dependents){this.dependents = dependents;}

    public void setMedicalCoverageType(String medicalCoverageType) {this.medicalCoverageType = medicalCoverageType;}

    public void setTimeEntry(LocalDate punchDate, double newHoursWorked) {
        TimeEntry updatedHours = timeClock.get(punchDate);
        updatedHours.setHoursWorked(newHoursWorked);
    }

    //getter functions 
    public int getEmployeeID() {return employeeID;}
    
    public String getFirstName() {return this.firstName;}

    public String getMiddleName() {return this.middleName;}

    public String getLastName() {return this.lastName;}

    public String getSuffix() {return this.suffix;}

    public String getDepartment() {return this.department;}

    public String getJobTitle() {return this.jobTitle;}

    public boolean getActive() {return this.active;}

    public LocalDate getHireDate() {return this.hireDate;}

    public double getBasePay() {return this.basePay;}

    public String getPayType() {return this.payType;}
    
    public LocalDate getDateOfBirth() {return this.dateOfBirth;}

    public String getGender() {return this.gender;}

    public String getAddress1() {return this.address1;}

    public String getAddress2() {return this.address2;}

    public String getCity() {return this.city;}

    public String getState() {return this.state;}

    public String getZip() {return this.zip;}

    public int getDependents() {return this.dependents;}

    public String getMedicalCoverageType() {return this.medicalCoverageType;}

    /*public Double getHoursWorked() { 
        return TimeEntry.tempHoursWorked();
    }*/
    public int getPTODays() { 
        return PTOEntry.tempPTO();
    }

    //doing functions
    public void newTimePunch(Scanner scanner, Dates dates){
        System.out.println("Enter date as YYYY-MM-dd");
        String dateString = scanner.nextLine();
        LocalDate punchDate = LocalDate.parse(dateString);
        //for testing
        System.out.println("You entered " + punchDate);
        if(dates.isValidTimeEntryDate(punchDate)) {
            System.out.println("Enter hours worked on " + punchDate);
            Double hoursEntered = scanner.nextDouble();
            scanner.nextLine();
            TimeEntry newTimeEntry = new TimeEntry(punchDate, hoursEntered);
            addTimeEntry(punchDate, newTimeEntry);
        }
        else {
            System.out.println("That date is outside of the current pay period");
        }
    }

    public void addTimeEntry(LocalDate timeEntryDate, TimeEntry newTimeEntry){
        timeClock.put(timeEntryDate, newTimeEntry);
    }

    //display functions
    public void printEmployeeInfo() {
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName + " " + suffix);
        System.out.println("Department: " + department);
        System.out.println("Job Title: " + jobTitle);
        if (active) {
            System.out.println("Status: Active");
        }
        else {
            System.out.println("Status: Terminated");            
        }
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Pay Type: " + payType);
        if (payType.equalsIgnoreCase("Hourly")) {
            System.out.println("Hourly Rate: $" + basePay);
        }
        else {
            System.out.println("Annual Salary: $" + basePay);  
        }
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);        
        System.out.print("Address: " + address1 + ", ");
        if (address2 != null) {
            System.out.print(address2 + ", ");
        }
        System.out.println(city + ", " + state + " " + zip);
        System.out.println("Dependents: " + dependents);
        System.out.println("Medical Coverage: " + medicalCoverageType);
        System.out.println();
    }

    public void printTimeRecords() {
        timeClock.forEach( (k, v) -> {v.printTimeRecord();} );
    }
}

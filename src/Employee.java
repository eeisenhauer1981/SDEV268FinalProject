import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

class Employee {
    private Company company;
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
    private String emailAddress;
    private Role role;    
    private HashMap<LocalDate, Double> timeClock = new HashMap<>();
    private HashMap<LocalDate, Integer> PTOList = new HashMap<>();
        
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
        Company newcompany,
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
            this.company = newcompany;
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
            setEmailAddress(newFirstName, newLastName, employeeID);
            this.emailAddress = this.getEmailAddress();
            this.role = Role.EMPLOYEE;
            AuthenticationManager.newUser(this.getEmailAddress(), this.getDateOfBirth(), this.getRole(), this.getEmployeeID(), this.getActive());
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

    public void setEmailAddress(String firstName, String lastName, int employeeID) {
        emailAddress = firstName + lastName + employeeID + "@" + company.getName().replaceAll("\\s", "") + ".com";
    }

    public void setTimePunch(LocalDate punchDate, Double hoursWorked) {
        timeClock.put(punchDate, hoursWorked);
    }

    public void setPTO(LocalDate dateOff, int countDayOff) {
        PTOList.put(dateOff, countDayOff);
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

    public String getActiveString() {
        if(active) {
            return "Active";
        }
        else {
            return "Terminated";
        }
    }

    public LocalDate getHireDate() {return this.hireDate;}

    public double getBasePay() {return this.basePay;}

    public String getPayType() {return this.payType;}
    
    public LocalDate getDateOfBirth() {return this.dateOfBirth;}

    public String getGender() {return this.gender;}

    public String getAddress1() {return this.address1;}

    public String getAddress2() {return this.address2;}

    public String getFullAddress() {
        if(address2.isBlank()) {
            return address1;
        }
        else {
            return address1 + ", " + address2;
        }
    }

    public String getCity() {return this.city;}

    public String getState() {return this.state;}

    public String getZip() {return this.zip;}

    public int getDependents() {return this.dependents;}

    public String getMedicalCoverageType() {return this.medicalCoverageType;}

    public String getEmailAddress() {return this.emailAddress;}

    public Role getRole() {return this.role;};

    public Double getHoursWorked(Dates dates) { 
        ArrayList<LocalDate> payPeriod = dates.getPayPeriod();
        System.out.println(payPeriod);
        Double totalHours = 0.0;
        for (int i = 0; i < 7; i++) {
            if(timeClock.containsKey(payPeriod.get(i))){
                totalHours = totalHours + timeClock.get(payPeriod.get(i));
            }
        }
        return totalHours;
    }
    public int getPTODays(Dates dates) { 
        ArrayList<LocalDate> payPeriod = dates.getPayPeriod();
        System.out.println(payPeriod);
        int totalDaysOff = 0;
        for (int i = 0; i < 7; i++) {
            if(PTOList.containsKey(payPeriod.get(i))){
                totalDaysOff = totalDaysOff + PTOList.get(payPeriod.get(i));
            }
        }
        return totalDaysOff;
    }

    public String getEmployeeInfo() {
        String empStatus = getActiveString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fullAddress = getFullAddress();
        

        return "Employee ID: " + employeeID + "\n"
            + "Name: " + firstName + " " + middleName + " " + lastName + " " + suffix + "\n"
            + "Department: " + department + "\n"
            + "Job Title: " + jobTitle + "\n"
            + "Employee Status: " + empStatus + "\n"
            + "Hire Date: " + hireDate.format(formatter) + "\n"
            + "Pay Type: " + payType + "\n"
            + "Base Pay: " + basePay + "\n"
            + "Birth Date: " + dateOfBirth.format(formatter) + "\n"
            + "Gender: " + gender + "\n"        
            + "Address: " + fullAddress + ", " + city + ", " + state + " " + zip + "\n"
            + "Dependents: " + dependents + "\n"
            + "Medical Coverage: " + medicalCoverageType + "\n"
            + "Email address: " + emailAddress + "\n";
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
            setTimePunch(punchDate, hoursEntered);
        }
        else {
            System.out.println("That date is outside of the current pay period");
        }
    }

    public void editTimePunch(Scanner scanner, Dates dates){
        System.out.println("Enter date as YYYY-MM-dd");
        String dateString = scanner.nextLine();
        LocalDate punchDate = LocalDate.parse(dateString);
        //for testing
        System.out.println("You entered " + punchDate);
        if(dates.isValidTimeEntryDate(punchDate)) {
            System.out.println("Enter hours worked on " + punchDate);
            Double hoursEntered = scanner.nextDouble();
            scanner.nextLine();
            if(timeClock.containsKey(punchDate)) {                
                timeClock.replace(punchDate, hoursEntered);
                System.out.println("Previous hours replaced.");
                System.out.println("New time card entry: " + timeClock.get(punchDate));
            }
            else {
                setTimePunch(punchDate, hoursEntered);
                System.out.println("No previous entry for " + punchDate);
                System.out.println("Time card entry added: " + timeClock.get(punchDate));
            }
        }
        else {
            System.out.println("That date can no longer be edited");
        }
    }

    public void newPTO(Scanner scanner, Dates dates){
        System.out.println("Enter start date as YYYY-MM-dd");
        String dateString = scanner.nextLine();
        //enter first date off
        LocalDate PTOStartDate = LocalDate.parse(dateString);
        //make sure date is current
        if(PTOStartDate.isAfter(LocalDate.now()) || PTOStartDate.isEqual(LocalDate.now())) {
            //get number of consecutive days off
            System.out.println("Enter number of days off");
            int daysOff = scanner.nextInt();
            scanner.nextLine();

            //add days off to employee PTO ArrayList
            for (LocalDate i = PTOStartDate; i.isBefore(PTOStartDate.plusDays(daysOff)); i.plusDays(1))
                setPTO(i, 1);
        }
        else {
            System.out.println("You cannot request time off for past dates");
        }
    }

    public void deletePTO(Scanner scanner, Dates dates){
        System.out.println("Enter date as YYYY-MM-dd");
        String dateString = scanner.nextLine();
        LocalDate PTODate = LocalDate.parse(dateString);

        if(PTOList.containsKey(PTODate)) {                
            PTOList.remove(PTODate);
            System.out.println("Day off removed on " + PTODate);
        }
        else {
            System.out.println("No PTO was entered for " + PTODate);
        }
    }

    //display functions
    

    public void printTimeRecords() {
        System.out.println(timeClock);
    }
}

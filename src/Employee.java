import java.time.LocalDate;
import java.util.ArrayList;

public class Employee {
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
    private ArrayList<TimeEntry> timeClock = new ArrayList();
    private ArrayList<PTOEntry> PTOList = new ArrayList();
        
    //default constructor
    public Employee() {
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
    public Employee (
        String firstName,
        String middleName,
        String lastName,
        String suffix,
        String department,
        String jobTitle,
        boolean active,
        LocalDate hireDate,
        String payType,
        double basePay,
        LocalDate dateOfBirth,
        String gender,
        String address1,
        String address2,
        String city,
        String state,
        String zip,
        int dependents,
        String medicalCoverageType
    ) {
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.suffix = suffix;
            this.department = department;
            this.jobTitle = jobTitle;
            this.active = active;
            this.hireDate = hireDate;
            this.payType = payType;
            this.basePay = basePay;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.address1 = address1;
            this.address2 = address2;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.dependents = dependents;
            this.medicalCoverageType = medicalCoverageType;       
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

    //getter functions 
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

    public Double getHoursWorked() { 
        return TimeEntry.tempHoursWorked();
    }
    public int getPTODays() { 
        return PTOEntry.tempPTO();
    }

    //display functions
    public void printEmployeeInfo() {
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
    }
}

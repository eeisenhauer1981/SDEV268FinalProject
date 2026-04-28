import java.time.LocalDate;

public class PayCheck {
    private int checkNumber;
    private LocalDate checkDate;
    private double dependentStipend;
    private double stateTax;
    private double federalTax;
    private double socialSecurity;
    private double medicare;
    private double medicalDeduction;
    private double grossPay;
    private double preTaxPay;
    private double netPay;
    private Employee payTo;
    private Company currentCompany;

    public PayCheck(Employee employee, Company company, Dates dates) {
        payTo = employee;
        currentCompany = company;
        checkNumber = company.increaseCheckNumber();
        checkDate = dates.getCurrentPayDate();
        calculatePayCheck(dates);        
    }

    //only calculates for current pay period using payPeriod set in Dates
    //previous pay period paychecks are saved and can be looked up
    //current
    public void calculatePayCheck(Dates dates) {
        dependentStipend = calculateDependentStipend(payTo);
        medicalDeduction = calculateMedicalDeduction(payTo);
        if(payTo.getPayType() == "Hourly") {
            grossPay = calculateHourlyWorkPay(payTo, dates) + dependentStipend;
        }
        else {
            grossPay = calculateSalaryWorkPay(payTo, dates) + dependentStipend;
        }
        preTaxPay = grossPay - medicalDeduction;
        stateTax = calculateStateTax(preTaxPay);
        federalTax = calculateFederalTax(preTaxPay);
        socialSecurity = calculateSocialSecurity(preTaxPay);
        medicare = calculateMedicare(preTaxPay);
        double taxes = stateTax + federalTax + socialSecurity + medicare;
        netPay = grossPay - medicalDeduction - taxes;
    }
    
    //calculate dependent stipend
    public double calculateDependentStipend(Employee payTo) {
        final double DEPENDENT_STIPEND_RATE = 45.0;
        return DEPENDENT_STIPEND_RATE * payTo.getDependents();
    }

    //calculate deductions
    public double calculateStateTax(double preTaxPay) {
        final double STATE_TAX_RATE = .0315;

        return STATE_TAX_RATE * preTaxPay;
    }

    public double calculateFederalTax(double preTaxPay) {
        final double FEDERAL_TAX_RATE = .0765;

        return FEDERAL_TAX_RATE * preTaxPay;
    }

    public double calculateSocialSecurity(double preTaxPay) {
        final double SOCIAL_SECURITY_RATE = .062;

        return SOCIAL_SECURITY_RATE * preTaxPay;
    }

    public double calculateMedicare(double preTaxPay) {
        final double MEDICARE_RATE = .0145;

        return MEDICARE_RATE * preTaxPay;
    }

    public double calculateMedicalDeduction(Employee payTo) {
        
        if (payTo.getMedicalCoverageType() == "Single") {
            return 50.0;
        } 
        else {
            return 100.0;
        }
    }

    //calculate work pay for hourly
    public double calculateHourlyWorkPay(Employee payTo, Dates dates) {
        final double MAX_REGULAR_HOURS = 40;
        double weeklyHours = payTo.getHoursWorked(dates);
        double payRate = payTo.getBasePay();

        if(weeklyHours <= MAX_REGULAR_HOURS) {
            return payRate * weeklyHours;
        }
        else {
            double straightPay = payRate * weeklyHours;
            double overtimePay = calculateOvertimePay(weeklyHours, payRate);
            return straightPay + overtimePay;
        }
    }

    public double calculateOvertimePay(double weeklyHours, double payRate) {
        final double MAX_REGULAR_HOURS = 40;
        final double OVERTIME_RATE = .5;
        double overtimeHours = weeklyHours - MAX_REGULAR_HOURS;
        return overtimeHours * payRate * OVERTIME_RATE;
    }

    //calculate work pay for salary
    public double calculateSalaryWorkPay(Employee payTo, Dates dates){
        int weeklyPTODays = payTo.getPTODays(dates);
        double payRate = payTo.getBasePay();

        if(weeklyPTODays == 0) {
            return payRate / 52;
        }
        else {
            double dailyPay = payRate / 52 / 5;
            double PTOPay = dailyPay * weeklyPTODays;
            int workDays = 5 - weeklyPTODays;
            double workDayPay = dailyPay * workDays;
            return workDayPay + PTOPay;
        }
    }

    //print paycheck info
    public void printPaycheck(){
        System.out.println("Check Number: " + checkNumber);
        System.out.println("Pay Date: " + checkDate);
        payTo.printEmployeeInfo();
        System.out.println("Dependent Stipend: " + dependentStipend);
        System.out.println("Medical Deduction: " + medicalDeduction);
        System.out.println("State Tax: " + stateTax);
        System.out.println("Federal Tax: " + federalTax);
        System.out.println("Social Security: " + socialSecurity);
        System.out.println("Medicare: " + medicare);
        System.out.println("Gross Pay: " + grossPay);
        System.out.println("Net Pay: " + netPay);
    }

}  
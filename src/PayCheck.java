import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.SortedMap;
import java.text.DecimalFormat;

class PayCheck {
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
    private String payFrom;

    public PayCheck(Employee employee, Company company, Dates dates) {
        payTo = employee;
        payFrom = company.getName();
        checkNumber = company.getCheckNumber();
        checkDate = dates.getCurrentPayDate();
        calculatePayCheck();        
    }

    public int getCheckNumber() {return checkNumber;}

    public void calculatePayCheck() {
        dependentStipend = calculateDependentStipend(payTo);
        medicalDeduction = calculateMedicalDeduction(payTo);
        if(payTo.getPayType().endsWith("Hourly")) {
            grossPay = calculateHourlyWorkPay(payTo) + dependentStipend;
        }
        else {
            grossPay = calculateSalaryWorkPay(payTo) + dependentStipend;
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
        
        if (payTo.getMedicalCoverageType().equals("Single")) {
            return 50.0;
        } 
        else {
            return 100.0;
        }
    }

    //calculate work pay for hourly
    public double calculateHourlyWorkPay(Employee payTo) {
        final double MAX_REGULAR_HOURS = 8;
        SortedMap <LocalDate, Double> weeklyHours = payTo.getHoursWorked();
        double payRate = payTo.getBasePay();
        double totalPay = 0.0;

        for(LocalDate i : weeklyHours.keySet()) {
            
            if(!weeklyHours.containsKey(i)) {
                continue;
            }
            else if(weeklyHours.get(i) <= 8 && i.getDayOfWeek() != DayOfWeek.SATURDAY) {
                double dailyPay = weeklyHours.get(i) * payRate;
                totalPay = totalPay + dailyPay;
            }
            else if (weeklyHours.get(i) > 8 && i.getDayOfWeek() != DayOfWeek.SATURDAY) {
                double overtimeHours = weeklyHours.get(i) - MAX_REGULAR_HOURS;
                double overtimePay = calculateOvertimePay(overtimeHours, payRate);
                double dailyPay = weeklyHours.get(i) * payRate;
                totalPay = totalPay + dailyPay + overtimePay;
            }
            else {
                double overtimeHours = weeklyHours.get(i);
                double overtimePay = calculateOvertimePay(overtimeHours, payRate);
                double dailyPay = weeklyHours.get(i) * payRate;
                totalPay = totalPay + dailyPay + overtimePay;
            }
        }
        return totalPay;
    }

    public double calculateOvertimePay(double overtimeHours, double payRate) {
        final double OVERTIME_RATE = .5;
        return overtimeHours * payRate * OVERTIME_RATE;
    }

    //calculate work pay for salary
    public double calculateSalaryWorkPay(Employee payTo){
        SortedMap <LocalDate, Integer> PTODays = payTo.getPTO();
        int weeklyPTODays = 0;

        for(LocalDate i : PTODays.keySet()) {
            if(!PTODays.containsKey(i)) {
                continue;
            }
            else {
                weeklyPTODays = weeklyPTODays + PTODays.get(i);
            }
        }

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

    //output paycheck info
    public String getPaycheckInfo(){
        DecimalFormat df = new DecimalFormat("#,###.##");
        return  payFrom + " Check Number: " + checkNumber + "\n"
                + "Pay Date: " + checkDate + "\n"
                + "Pay To: " + payTo.getFirstName() + " " + payTo.getMiddleName() + " " + payTo.getLastName() + " " + payTo.getSuffix() + "\n"
                + "Dependent Stipend: $" + df.format(dependentStipend) + "\n"
                + "Medical Deduction: $" + df.format(medicalDeduction) + "\n"
                + "State Tax: $" + df.format(stateTax) + "\n"
                + "Federal Tax: $" + df.format(federalTax) + "\n"
                + "Social Security: $" + df.format(socialSecurity) + "\n"
                + "Medicare: $" + df.format(medicare) + "\n"
                + "Gross Pay: $" + df.format(grossPay) + "\n"
                + "Net Pay: $" + df.format(netPay) + "\n";
    }

    public String savePaycheckInfo(){
        DecimalFormat df = new DecimalFormat("#,###.##");
        return  checkNumber + ","
                + checkDate + ","
                + payTo.getFirstName() + "," 
                + payTo.getMiddleName() + ","
                + payTo.getLastName() + ","
                + payTo.getSuffix() + ","
                + df.format(dependentStipend) + ","
                + df.format(medicalDeduction) + ","
                + df.format(stateTax) + ","
                + df.format(federalTax) + ","
                + df.format(socialSecurity) + ","
                + df.format(medicare) + ","
                + df.format(grossPay) + ","
                + df.format(netPay);
    }

}  
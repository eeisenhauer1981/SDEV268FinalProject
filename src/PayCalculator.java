public class PayCalculator {
    
    //calculate dependent stipend
    public double dependentStipend(int dependents) {
        final double DEPENDENT_STIPEND_RATE = 45.0;
        return DEPENDENT_STIPEND_RATE * dependents;
    }

    //calculate deductions
    public double stateTax(double preTaxPay) {
        final double STATE_TAX_RATE = .0315;

        return STATE_TAX_RATE * preTaxPay;
    }

    public double federalTax(double preTaxPay) {
        final double FEDERAL_TAX_RATE = .0765;

        return FEDERAL_TAX_RATE * preTaxPay;
    }

    public double socialSecurity(double preTaxPay) {
        final double SOCIAL_SECURITY_RATE = .062;

        return SOCIAL_SECURITY_RATE * preTaxPay;
    }

    public double medicare(double preTaxPay) {
        final double MEDICARE_RATE = .0145;

        return MEDICARE_RATE * preTaxPay;
    }

    public double medicalDeduction(String medCoverageType) {
        
        if (medCoverageType == "Single") {
            return 50.0;
        } 
        else {
            return 100.0;
        }
    }

    //calculate hourly pay
    public double hourlyWorkPay(double basePay, double hoursWorked) {
        final double MAX_REGULAR_HOURS = 40;

        if(hoursWorked <= MAX_REGULAR_HOURS) {
            return basePay * hoursWorked;
        }
        else {
            double straightPay = basePay * hoursWorked;
            double overtimePay = overtimePay(hoursWorked, basePay);
            return straightPay + overtimePay;
        }
    }

    public double overtimePay(double hoursWorked, double hourlyRate) {
        final double MAX_REGULAR_HOURS = 40;
        final double OVERTIME_RATE = .5;
        double overtimeHours = hoursWorked - MAX_REGULAR_HOURS;
        return overtimeHours * hourlyRate * OVERTIME_RATE;
    }

    public double hourlyNetPay(double basePay, double hoursWorked, int dependents, String medicalCoverageType) {
        double grossPay = hourlyWorkPay(basePay, hoursWorked) + dependentStipend(dependents);
        double preTaxPay = grossPay - medicalDeduction(medicalCoverageType);
        return preTaxPay - stateTax(preTaxPay) - federalTax(preTaxPay) - socialSecurity(preTaxPay) - medicare(preTaxPay);
    }

    //calculate salary pay
    public double salaryWorkPay(double basePay, int PTODays){
        if(PTODays == 0) {
            return basePay / 52;
        }
        else {
            double dailyPay = basePay / 52 / 5;
            double PTOPay = dailyPay * PTODays;
            int workDays = 5 - PTODays;
            double workDayPay = dailyPay * workDays;
            return workDayPay + PTOPay;
        }
    }

    public double salaryNetPay(double basePay, int PTODays, int dependents, String medicalCoverageType) {
        double grossPay = salaryWorkPay(basePay, PTODays) + dependentStipend(dependents);
        double preTaxPay = grossPay - medicalDeduction(medicalCoverageType);
        return preTaxPay - stateTax(preTaxPay) - federalTax(preTaxPay) - socialSecurity(preTaxPay) - medicare(preTaxPay);
    }
}  
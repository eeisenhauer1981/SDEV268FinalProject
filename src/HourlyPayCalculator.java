public class HourlyPayCalculator {
    public static void main(String[] args) {
        //calculate dependent stipend
        final double DEPENDENT_STIPEND_RATE = 45.0;
        int dependent = 1;
        double dependentStipend = DEPENDENT_STIPEND_RATE * dependent;

        //CALCULATE GROSS PAY
        //variables
        double hoursWorked = 10;
        double hourlyRate = 20.0;
        double hourlyPay;
        double grossPay;
        final double MAX_REGULAR_HOURS = 8;
        final double OVERTIME_RATE = .5;

        //calculate pay for hours worked
        if (hoursWorked > MAX_REGULAR_HOURS) {
            double overtimeHours = hoursWorked - MAX_REGULAR_HOURS;
            double overtimePay = overtimeHours * hourlyRate * OVERTIME_RATE;
            double regularPay = hoursWorked * hourlyRate;
            hourlyPay = regularPay + overtimePay;
        } else {
            hourlyPay = hoursWorked * hourlyRate;
        }

        //gross pay = pay for hours worked + dependent stipend !!verify this is correct calculation!!
        grossPay = hourlyPay + dependentStipend;

        //CALCULATE PRE-TAX AMOUNT
        //variables
        char medicalType = 'f';
        double medicalDeduction;
        
        //assign medical deduction amount
        if (medicalType == 's') {
            medicalDeduction = 50.0;
        } else {
            medicalDeduction = 100.0;
        }

        //calculate pre tax pay amount
        double preTaxPay = grossPay - medicalDeduction;

        //CALCULATE TAXES
        //variables
        final double STATE_TAX_RATE = .0315;
        final double FEDERAL_TAX_RATE = .0765;
        final double SOCIAL_SECURITY_RATE = .062;
        final double MEDICARE_RATE = .0145;

        //calculate tax amounts
        double stateTaxAmount = STATE_TAX_RATE * preTaxPay;
        double federalTaxAmount = FEDERAL_TAX_RATE * preTaxPay;
        double socialSecurityAmount = SOCIAL_SECURITY_RATE * preTaxPay;
        double medicaidAmount = MEDICARE_RATE * preTaxPay;

        //CALCULATE NET PAY
        double totalTaxDeduction = stateTaxAmount + federalTaxAmount + socialSecurityAmount + medicaidAmount;
        double netPay = grossPay - totalTaxDeduction;      

        //OUTPUTS
        System.out.printf ("Hourly Earnings: $%.2f\n", hourlyPay);
        System.out.printf ("Dependent Stipend: $%.2f\n", dependentStipend);
        System.out.printf ("Employee Gross Pay: $%.2f\n", grossPay);
        System.out.println("DEDUCTIONS");
        System.out.printf ("Medical Deduction: $%.2f\n", medicalDeduction);
        System.out.println("State Tax");
        System.out.printf ("     Employee: $%.2f\n", stateTaxAmount);
        System.out.printf ("     Employer: $%.2f\n", stateTaxAmount);
        System.out.println("Federal Tax");
        System.out.printf ("     Employee: $%.2f\n", federalTaxAmount);
        System.out.printf ("     Employer: $%.2f\n", federalTaxAmount);
        System.out.println("Social Security");
        System.out.printf ("     Employee: $%.2f\n", socialSecurityAmount);
        System.out.printf ("     Employer: $%.2f\n", socialSecurityAmount);
        System.out.println("Medicaid");
        System.out.printf ("     Employee: $%.2f\n", medicaidAmount);
        System.out.printf ("     Employer: $%.2f\n", medicaidAmount);
        System.out.printf ("Employee Net Pay: $%.2f\n", netPay);
    }
}

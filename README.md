# Payroll Processing Program

This project is my final project submission to meet requirements for SDEV268 at Ivy Tech Community College.

It is an employee management and payroll calculation simulation program. Users include HR administrators and employees, each with different levels of access.

Verified HR administrators have access to:  
  * View employee demographic and salary information
  * Add new employees and edit or delete existing employees
  * Edit employee time card punches and PTO requests
  * Process and approve payroll and output a paycheck file
  * View application information

Employees have access to:  
  * Add and edit time card punches
  * Add and edit PTO requests
  * Calculate a sample paycheck

For demonstration, the program will automatically load 11 test employees from a text file, "Payroll Project Load Employees - Emily Eisenhauer" stored in the src folder, and hard coded time punches are also loaded so that all features are immediately available.

Try testing:  
  * Login as an admin with username HR0001 and password Adm1n!
  * Click any of the buttons in the Admin Main Menu to see functionality
  * Login as an employee with any username (employee's email address) and password (employee's birthdate in MMddyyyy format). For example: BenjaminPrice2@MarshmallowHaven.com/04121989.
  * Click any of the buttons in the Employee Main Menu to see functionality

Notes on calculations:  
  * Pay periods run from Saturday to Friday, with paychecks issued the following Friday. For example, pay period 4/25/26 - 5/1/26 will have a pay date of 5/8/26.
  * Paychecks are automatically processed for the prior pay period based on process date. For example, if payroll is run between 5/2/26 and 5/8/26, payroll will be processed for pay period 4/25/26 - 5/1/26 and will have a pay date of 5/8/26. Employee sample paychecks are calculated on the same timeline.
  * Time card entries are validated by date based on date of entry. Additions and edits can only be made for the current pay period and cannot be made for future dates.
  * PTO entries are validated by date based on date of entry. Additions and edits can be made for any date on or after the date of entry.
  

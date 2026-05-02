public class User {
    private String username;
    private String hashPassword;
    private Role role;
    private int employeeID;
    private boolean isActive;

    public User(String employeeEmail, String employeeHashPassword, Role employeeRole, int employeeID, boolean employeeStatus) {
        this.username = employeeEmail;
        this.hashPassword = employeeHashPassword;
        this.role = employeeRole;
        this.employeeID = employeeID;
        this.isActive = employeeStatus;
    }
    
    public String getUsername() {return username;}

    public String getHashPassword() {return hashPassword;}

    public Role getRole() {return role;}

    public int getEmployeeID() {return employeeID;}

    public boolean getIsActive() {return isActive;}

}

import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AuthenticationManager {

    //list of credentials. key is username(employee email) and value is User object
    private HashMap<String, User> users = new HashMap<>();

    //adds new user credentials to map
    public void addUser(String employeeEmail, LocalDate employeeBirthday, Role employeeRole, int employeeID, boolean employeeStatus){
        //turns employee birthday into a string - per assignment instructions, password should be based on employee birthday
        String formattedBirthday = formatBirthday(employeeBirthday);
        //calls hash method to hash password string into a secure hashed password for storage
        String employeeHashPassword = SecurityUtil.hashMD5(formattedBirthday);
        User newUser = new User(employeeEmail, employeeHashPassword, employeeRole, employeeID, employeeStatus);
        users.put(employeeEmail, newUser);
    }

    //formats employee birthday as string for hashing
    public String formatBirthday(LocalDate employeeBirthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return employeeBirthday.format(formatter);
    }

    //authenticates user credentials 
    public User authenticate(String username, String inputPassword) {
        User foundUser = users.get(username);

        //checks to see if username is in map
        if(foundUser == null) {
            return null;
        }

        //checks to see if user is active or terminated
        if (!foundUser.getIsActive()) {
            return null;
        }

        //hashes input password and compare to stored hashed password
        String verifyPassword = SecurityUtil.hashMD5(inputPassword);

        if(verifyPassword.equals(foundUser.getHashPassword())) {
            return foundUser;
        }
        
        return null;
    
    }
    
}

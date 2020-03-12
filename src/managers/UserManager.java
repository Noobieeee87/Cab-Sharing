package managers;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> userData;
    private static UserManager userManager;

    private UserManager(){
        userData = new HashMap<>();
    }

    public  static UserManager getInstance(){
        if (userManager == null) userManager = new UserManager();
        return userManager;
    }

    public void addUser(User user){
        userData.put(user.getUserId(),user);
    }
}

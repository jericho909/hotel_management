package business;

import dao.UserDao;
import entities.User;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User fetchUserWithLoginInfo(String username, String password){
        return this.userDao.fetchUserWithLoginInfo(username,password);
    }
}

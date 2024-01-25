package business;

import core.Helper;
import dao.UserDao;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {

    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User fetchUserWithLoginInfo(String username, String password){
        return this.userDao.fetchUserWithLoginInfo(username,password);
    }

    public ArrayList<User> fetchAllUsers(){
        return this.userDao.fetchAllUsers();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> list){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUser_name();
            rowObject[i++] = user.getUser_role();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    public User getById(int userId){
        return this.userDao.getById(userId);
    }

    public boolean saveUser(User user){
        if (user.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }

        return this.userDao.saveUser(user);
    }

    public boolean editUser(User user){
        if (this.getById(user.getId()) == null) {
            Helper.showErrorMessage("ID not found.");
        }

        return this.userDao.editUser(user);
    }

    public boolean deleteUser(int userId){
        if (this.getById(userId) == null) {
            Helper.showErrorMessage("Cannot find ID.");
            return false;
        } else {
            return this.userDao.deleteUser(userId);
        }
    }

    public ArrayList<User> searchUsersByRole(String userRole){
        String query = "SELECT * FROM public.users WHERE user_role = '" + userRole + "';";
        return this.userDao.queryDatabase(query);
    }


}

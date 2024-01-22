package dao;

import core.DbConnection;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    public final Connection connection;


    public UserDao() {
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<User> fetchAllUsers(){
        ArrayList<User> userArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.users";
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                userArrayList.add(convertDatabaseValueToClass(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return userArrayList;
    }

    public User fetchUserWithLoginInfo(String username, String password){
        User user = null;
        String query = "SELECT * FROM public.users WHERE user_name = ? AND user_password = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                user = this.convertDatabaseValueToClass(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public User convertDatabaseValueToClass(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUser_name(rs.getString("user_name"));
        user.setUser_password(rs.getString("user_password"));
        user.setUser_role(rs.getString("user_role"));

        return user;
    }
}

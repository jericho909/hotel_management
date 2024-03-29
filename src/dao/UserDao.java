package dao;

import core.DbConnection;
import entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {

    public final Connection connection;
    //the same with the hotelDao, the methods unique to this dao will be explained below.
    // if no explanation is given you can look them up in hoteldao

    public UserDao() {
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<User> fetchAllUsers(){
        ArrayList<User> userArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.users";
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                userArrayList.add(convertDatabaseValueToUser(rs));
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
                user = this.convertDatabaseValueToUser(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public User convertDatabaseValueToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUser_name(rs.getString("user_name"));
        user.setUser_password(rs.getString("user_password"));
        user.setUser_role(rs.getString("user_role"));

        return user;
    }

    public boolean saveUser(User user){
        String query = "INSERT INTO public.users (user_name, user_password, user_role) VALUES (?,?,?)";

        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.setString(3, user.getUser_role());

            return preparedStatement.executeUpdate() != 1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean editUser(User user){
        String query = "UPDATE public.users SET user_name = ?, user_password = ?, user_role = ? WHERE id = ?";


        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_password());
            preparedStatement.setString(3, user.getUser_role());
            preparedStatement.setInt(4, user.getId());
            return preparedStatement.executeUpdate() != 1;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public boolean deleteUser(int userId){
        String query = "DELETE FROM public.users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public User getById(int userId){
        User user = null;
        String query = "SELECT * FROM public.users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                user = this.convertDatabaseValueToUser(rs);
            }
        } catch (SQLException e){

        }
        return user;
    }

    public ArrayList<User> customQueryFetchDatabase(String query){
        ArrayList<User> userArrayList = new ArrayList<>();

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                userArrayList.add(convertDatabaseValueToUser(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return userArrayList;
    }
}

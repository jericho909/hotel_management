package dao;

import core.ComboItem;
import core.DbConnection;
import entities.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeDao {

    public final Connection connection;

    public TypeDao(){
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<Type> fetchAllTypes(){
        ArrayList<Type> typeArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.types";
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                typeArrayList.add(convertDatabaseValueToType(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return typeArrayList;
    }

    public Type convertDatabaseValueToType(ResultSet rs) throws SQLException {
        Type type = new Type();
        type.setId(rs.getInt("id"));
        type.setHotel_id(rs.getInt("hotel_id"));
        type.setType_hotel_name(rs.getString("type_hotel_name"));

        return type;
    }

    public boolean saveType(Type type){
        String query = "INSERT INTO public.types (hotel_id, type_hotel_name) VALUES (?,?)";

        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, type.getHotel_id());
            preparedStatement.setString(2, type.getType_hotel_name());

            return preparedStatement.executeUpdate() != 1;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public Type getByTypeId(int typeId){
        Type type = null;
        ArrayList<Type> typeArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.types WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, typeId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                type = convertDatabaseValueToType(rs);
            }
        } catch (SQLException e){

        }
        return type;
    }

    public ArrayList<ComboItem> getTypesByHotelId(int hotelId){
        int id;
        String typeName;
        ArrayList<ComboItem> typeArrayList = new ArrayList<>();
        String query = "SELECT id, type_hotel_name FROM public.types WHERE hotel_id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                id = rs.getInt("id");
                typeName = rs.getString("type_hotel_name");
                ComboItem comboItem = new ComboItem(id, typeName);
                typeArrayList.add(comboItem);
            }
        } catch (SQLException e){

        }
        return typeArrayList;
    }
}

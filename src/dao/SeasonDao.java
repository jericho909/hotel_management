package dao;

import core.ComboItem;
import core.DbConnection;
import entities.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

    public final Connection connection;
    //the same with the hotelDao, the methods unique to this dao will be explained below.
    // if no explanation is given you can look them up in hoteldao
    public SeasonDao(){
        this.connection = DbConnection.getInstance();
    }

    public ArrayList<Season> fetchAllSeasons(){
        ArrayList<Season> seasonArrayList = new ArrayList<>();

        try {
            String query = "SELECT * FROM public.season";
            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()){
                seasonArrayList.add(convertDatabaseValueToSeason(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return seasonArrayList;
    }

    public Season convertDatabaseValueToSeason(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setId(rs.getInt("id"));
        season.setHotel_id(rs.getInt("hotel_id"));
        season.setSeason_start_date(LocalDate.parse(rs.getString("season_start_date")));
        season.setSeason_end_date(LocalDate.parse(rs.getString("season_end_date")));
        season.setSeason_name(rs.getString("season_name"));
        season.setSeason_rate(rs.getDouble("season_rate"));

        return season;
    }

    public boolean saveSeason(Season season){
        String query = "INSERT INTO public.season (hotel_id, season_start_date, season_end_date, season_name, " +
                "season_rate) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, season.getHotel_id());
            preparedStatement.setDate(2, Date.valueOf(season.getSeason_start_date()));
            preparedStatement.setDate(3, Date.valueOf(season.getSeason_end_date()));
            preparedStatement.setString(4, season.getSeason_name());
            preparedStatement.setDouble(5, season.getSeason_rate());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Season getById(int seasonId){
        Season season = null;

        String query = "SELECT * FROM public.season WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, seasonId);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                season = this.convertDatabaseValueToSeason(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return season;
    }

    public ArrayList<ComboItem> getSeasonByHotelId(int hotelId){
        int id;
        String seasonName;
        String seasonStartDate;
        String seasonEndDate;
        String seasonStr;
        ArrayList<ComboItem> seasonArrayList = new ArrayList<>();
        String query = "SELECT id, season_name, season_start_date, season_end_date FROM public.season WHERE hotel_id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotelId);

            ResultSet rs = preparedStatement.executeQuery();
            //this is for custom formatting of the values in the table, so the UI will be more informative
            //take note that the comboItem structure is still the name but the str contains more info
            while (rs.next()){
                id = rs.getInt("id");
                seasonName = rs.getString("season_name");
                seasonStartDate = String.valueOf(rs.getDate("season_start_date"));
                seasonEndDate = String.valueOf(rs.getDate("season_end_date"));
                seasonStr = seasonName + ": " + seasonStartDate + " / " + seasonEndDate;
                seasonArrayList.add(new ComboItem(id, seasonStr));
            }
        } catch (SQLException e){

        }

        return seasonArrayList;
    }

}

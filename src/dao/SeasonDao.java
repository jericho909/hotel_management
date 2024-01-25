package dao;

import core.DbConnection;
import entities.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

    public final Connection connection;

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

        return season;
    }

    public boolean saveSeason(Season season){
        String query = "INSERT INTO public.season (hotel_id, season_start_date, season_end_date) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, season.getHotel_id());
            preparedStatement.setDate(2, Date.valueOf(season.getSeason_start_date()));
            preparedStatement.setDate(3, Date.valueOf(season.getSeason_end_date()));

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
}

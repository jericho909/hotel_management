package business;

import core.ComboItem;
import core.Helper;
import dao.HotelDao;
import dao.SeasonDao;
import entities.Season;
import entities.Type;

import java.util.ArrayList;

public class SeasonManager {

    private final SeasonDao seasonDao;
    private final HotelManager hotelManager;
    //mostly same as hotelManager, if no comment is given check hotelManager for explanation
    public SeasonManager() {
        this.hotelManager = new HotelManager();
        this.seasonDao = new SeasonDao();
    }

    public boolean saveSeason(Season season){
        if (season.getId() != 0){
            Helper.showErrorMessage("Invalid entry.");
        }
        return this.seasonDao.saveSeason(season);
    }

    public ArrayList<Season> fetchAllSeasons(){
        return this.seasonDao.fetchAllSeasons();
    }

    public Season getById(int seasonId){
        return this.seasonDao.getById(seasonId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> list){
        ArrayList<Object[]> seasonRowList = new ArrayList<>();

        for (Season season: list){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = season.getId();
            rowObject[i++] = this.hotelManager.getById(season.getHotel_id()).getHotel_name();
            rowObject[i++] = season.getSeason_start_date();
            rowObject[i++] = season.getSeason_end_date();
            rowObject[i++] = season.getSeason_name();
            rowObject[i++] = season.getSeason_rate();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }

    public ArrayList<ComboItem> getSeasonsByHotelId(int hotelId){
        return this.seasonDao.getSeasonByHotelId(hotelId);
    }
}

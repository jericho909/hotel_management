package view;

import business.*;
import core.Helper;
import entities.Hotel;
import entities.Room;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class UserView extends Layout{
    private DefaultTableModel default_table_hotel = new DefaultTableModel();
    private DefaultTableModel default_table_types = new DefaultTableModel();
    private DefaultTableModel default_table_season = new DefaultTableModel();
    private DefaultTableModel default_table_rooms = new DefaultTableModel();
    private DefaultTableModel default_table_reservations = new DefaultTableModel();
    private HotelManager hotelManager;
    private Hotel hotel;
    private User user;
    private UserManager userManager;
    private TypeManager typeManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JScrollPane scl_hotel;
    private JButton btn_addhotel;
    private JPanel pnl_types;
    private JTable tbl_types;
    private JScrollPane scl_types;
    private JButton btn_addtype;
    private JPanel pnl_hotel_top;
    private JPanel pnl_type_top;
    private JButton btn_addseason;
    private JPanel pnl_season;
    private JScrollPane scl_seasons;
    private JTable tbl_season;
    private JPanel pnl_season_top;
    private JTable tbl_rooms;
    private JPanel pnl_rooms;
    private JScrollPane scl_rooms;
    private JPanel pnl_rooms_top;
    private JButton btn_addroom;
    private JButton btn_addreservation;
    private JTable tbl_reservations;
    private JPanel pnl_reservations;
    private JScrollPane scl_reservations;
    private JComboBox cmb_search_hotelName;
    private JComboBox cmb_search_hotelCity;
    private JButton btn_search;
    private JFormattedTextField fmt_fld_startDate;
    private JFormattedTextField fmt_fld_endDate;
    private JButton btn_searchClear;
    private JComboBox cmb_search_hotelsInType;
    private JButton btn_search_hotel_types;
    private JButton btn_clearSearchHotelTypes;
    private ReservationManager reservationManager;
    private JPopupMenu reservation_menu;

    public UserView(User user) {
        this.seasonManager = new SeasonManager();
        this.typeManager = new TypeManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.user = user;
        this.add(container);
        layoutStart((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(),(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()));

        if (this.user == null){
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUser_name());

        initializeHotelTable();
        initializeTypesTable(null);
        initializeSeasonsTable();
        initializeRoomsTable(null);
        initializeReservationsTable();
        initializeReservationMenuOptions();
        populateSearchComboBoxes();
        populateTypeSearchComboBoxes();
        cmb_search_hotelCity.setSelectedItem(null);
        cmb_search_hotelName.setSelectedItem(null);
        cmb_search_hotelsInType.setSelectedItem(null);

        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

        btn_addhotel.addActionListener(e -> {
            HotelAddMenu hotelAddMenu = new HotelAddMenu();
            hotelAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeHotelTable();
                    populateSearchComboBoxes();
                }
            });
        });

        btn_addtype.addActionListener(e -> {
            TypeAddMenu typeAddMenu = new TypeAddMenu();
            typeAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeTypesTable(null);
                }
            });
        });
        btn_addseason.addActionListener(e -> {
            SeasonAddMenu seasonAddMenu = new SeasonAddMenu();
            seasonAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeSeasonsTable();
                }
            });
        });

        btn_addroom.addActionListener(e -> {
            RoomAddMenu roomAddMenu = new RoomAddMenu();
            roomAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeRoomsTable(null);
                }
            });
        });

        btn_addreservation.addActionListener(e -> {
            ReservationAddEditMenu reservationAddEditMenu = new ReservationAddEditMenu(null);
            reservationAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeReservationsTable();
                    initializeRoomsTable(null);
                }
            });
        });

        btn_search.addActionListener(e -> {
            Object[] columnsOfRoomsTable = {"Room ID", "Hotel Name", "Room Boarding Type", "Room Season",
                    "Room Adult Price", "Room Child Price", "Room Type","Room Bed Count", "Room TV", "Room Minibar",
                    "Room Gaming Console", "Room Square Footage", "Room Safe", "Room Projection", "Room Stock"};
            LocalDate startDate = null;
            LocalDate endDate = null;
            String selectedHotel = this.getValueFromComboBox(cmb_search_hotelName);
            String selectedCity = this.getValueFromComboBox(cmb_search_hotelCity);
            if (!fmt_fld_startDate.getText().equals("  /  /    ")){
                startDate = LocalDate.parse(fmt_fld_startDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            if (!fmt_fld_endDate.getText().equals("  /  /    ")){
                endDate = LocalDate.parse(fmt_fld_endDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            ArrayList<Room> roomsListSearch = this.roomManager.roomFilterByCustomProperties(selectedHotel, selectedCity, startDate, endDate);
            ArrayList<Object[]> roomRowBySearch = this.roomManager.getForTable(columnsOfRoomsTable.length, roomsListSearch);
            if (selectedHotel == null && selectedCity == null && startDate == null && endDate == null){
                initializeRoomsTable(null);
            }
            initializeRoomsTable(roomRowBySearch);
        });

        btn_searchClear.addActionListener(e -> {
            cmb_search_hotelName.setSelectedItem(null);
            cmb_search_hotelCity.setSelectedItem(null);
            initializeRoomsTable(null);
            this.fmt_fld_startDate.setText("  /  /    ");
            this.fmt_fld_endDate.setText("  /  /    ");
        });

        btn_search_hotel_types.addActionListener(e-> {
            Object[] columnsOfTypesTable = {"Type ID", "Hotel Name", "Hotel Types"};
            String hotelName = getValueFromComboBox(cmb_search_hotelsInType);
            if (hotelName == null){
                initializeTypesTable(null);
            } else {
                int selectedHotelId = this.hotelManager.queryDatabaseForId(hotelName);
                ArrayList<entities.Type> typeListSearch = this.typeManager.searchTypesByHotelId(selectedHotelId);
                ArrayList<Object[]> typeRowBySearch = this.typeManager.getForTable(columnsOfTypesTable.length, typeListSearch);
                initializeTypesTable(typeRowBySearch);
            }


        });

        btn_clearSearchHotelTypes.addActionListener(e-> {
            cmb_search_hotelsInType.setSelectedItem(null);
            initializeTypesTable(null);

        });
    }

    private void initializeHotelTable(){
        Object[] columnsOfHotelTable = {"Hotel ID", "Hotel Name", "Hotel City", "Hotel Address", "Hotel E-Mail", "Hotel Phone Number",
                "Hotel Star", "Hotel Free Parking", "Hotel Free Wifi", "Hotel Swimming Pool", "Hotel Gym",
                "Hotel Concierge Service", "Hotel Spa", "Hotel 7/24 Room Service"};

        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(columnsOfHotelTable.length, this.hotelManager.fetchAllHotels());
        this.createTable(this.default_table_hotel, this.tbl_hotel, columnsOfHotelTable, hotelList);
    }

    private void initializeTypesTable(ArrayList<Object[]> typeList){
        Object[] columnsOfTypesTable = {"Type ID", "Hotel Name", "Hotel Types"};
        if (typeList == null){
            typeList = this.typeManager.getForTable(columnsOfTypesTable.length, this.typeManager.fetchAllTypes());
        }
        this.createTable(this.default_table_types, this.tbl_types, columnsOfTypesTable, typeList);
    }

    private void initializeSeasonsTable(){
        Object[] columnsOfSeasonsTable = {"Season ID", "Hotel Name", "Season Start", "Season End", "Season Name", "Season Rate"};

        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(columnsOfSeasonsTable.length, this.seasonManager.fetchAllSeasons());
        this.createTable(this.default_table_season, this.tbl_season, columnsOfSeasonsTable, seasonList);
    }

    private void initializeRoomsTable(ArrayList<Object[]> roomList){
        Object[] columnsOfRoomsTable = {"Room ID", "Hotel Name", "Room Boarding Type", "Room Season",
                "Room Adult Price", "Room Child Price", "Room Type","Room Bed Count", "Room TV", "Room Minibar",
                "Room Gaming Console", "Room Square Footage", "Room Safe", "Room Projection", "Room Stock"};
        if (roomList == null){
            roomList = this.roomManager.getForTable(columnsOfRoomsTable.length, this.roomManager.fetchAllRooms());
        }
        this.createTable(this.default_table_rooms, this.tbl_rooms, columnsOfRoomsTable, roomList);
    }

    private void initializeReservationsTable(){
        Object[] columnsOfReservationsTable = {"Reservation ID", "Room", "Start Date", "End Date", "Guest Name", "Phone", "Total Price"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(columnsOfReservationsTable.length, this.reservationManager.fetchAllReservations());

        this.createTable(this.default_table_reservations, this.tbl_reservations, columnsOfReservationsTable, reservationList);
    }

    private void initializeReservationMenuOptions(){
        tableRowSelect(this.tbl_reservations);

        this.reservation_menu = new JPopupMenu();

        this.reservation_menu.add("Edit Reservation").addActionListener(e->{
            int selectedReservationId = this.getTableSelectedRow(tbl_reservations, 0);
            ReservationAddEditMenu reservationAddEditMenu = new ReservationAddEditMenu(this.reservationManager.getById(selectedReservationId));
            reservationAddEditMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeReservationsTable();
                    initializeReservationMenuOptions();
                }
            });
        });

        this.reservation_menu.add("Delete Reservation").addActionListener(e->{
            if (Helper.confirm("Yes")){
                int selectedReservationId = this.getTableSelectedRow(tbl_reservations, 0);
                int roomStock = this.roomManager.getById(this.reservationManager.getById(selectedReservationId).getRoom_id()).getRoom_stock();
                int selectedReservationRoomId = this.reservationManager.getById(selectedReservationId).getRoom_id();
                if (this.reservationManager.deleteReservation(selectedReservationId)){
                    Helper.showCustomMessage("Reservation deleted.", "Operation successful.");
                    this.roomManager.changeStock(roomStock + 1, selectedReservationRoomId);
                    initializeRoomsTable(null);
                    initializeReservationsTable();
                    initializeReservationMenuOptions();
                } else {
                    Helper.showCustomMessage("User cancelled operation.", "Aborted by user.");
                }
            }
        });

        this.tbl_reservations.setComponentPopupMenu(reservation_menu);
    }

    private void populateSearchComboBoxes(){
        ArrayList<Hotel> hotelArrayList = this.hotelManager.fetchAllHotels();
        ArrayList<String> cityList = new ArrayList<>();
        ArrayList<String> hotelNameList = new ArrayList<>();

        for (Hotel hotel: hotelArrayList){
            if (!cityList.contains(hotel.getHotel_city())){
                cityList.add(hotel.getHotel_city());
            }
            if (!hotelNameList.contains(hotel.getHotel_name())){
                hotelNameList.add(hotel.getHotel_name());
            }
        }

        for (String hotelCity: cityList){
            this.cmb_search_hotelCity.addItem(hotelCity);
        }

        for (String hotelName: hotelNameList){
            this.cmb_search_hotelName.addItem(hotelName);
        }
    }

    private void populateTypeSearchComboBoxes(){
        HashSet<String> hotelNames = new HashSet<>();
        ArrayList<Hotel> hotelArrayList = this.hotelManager.fetchAllHotels();
        for (Hotel hotel: hotelArrayList){
            hotelNames.add(hotel.getHotel_name());
        }
        for (String hotelName: hotelNames){
            cmb_search_hotelsInType.addItem(hotelName);
        }
    }

    private String getValueFromComboBox(JComboBox comboBox){
        if (comboBox.getSelectedItem() == null){
            return null;
        }
       return comboBox.getSelectedItem().toString();
    }

    private void createUIComponents() throws ParseException {
        this.fmt_fld_startDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_fld_startDate.setText("**/**/****");
        this.fmt_fld_endDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_fld_endDate.setText("**/**/****");
    }
}

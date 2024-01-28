package view;

import business.*;
import entities.Hotel;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private ReservationManager reservationManager;

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
        initializeTypesTable();
        initializeSeasonsTable();
        initializeRoomsTable();
        initializeReservationsTable();

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
                }
            });
        });

        btn_addtype.addActionListener(e -> {
            TypeAddMenu typeAddMenu = new TypeAddMenu();
            typeAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeTypesTable();
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
                    initializeRoomsTable();
                }
            });
        });

        btn_addreservation.addActionListener(e -> {
            ReservationAddMenu reservationAddMenu = new ReservationAddMenu();
            reservationAddMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    initializeReservationsTable();
                    initializeRoomsTable();
                }
            });
        });
    }

    private void initializeHotelTable(){
        Object[] columnsOfHotelTable = {"Hotel ID", "Hotel Name", "Hotel City", "Hotel Address", "Hotel E-Mail", "Hotel Phone Number",
                "Hotel Star", "Hotel Free Parking", "Hotel Free Wifi", "Hotel Swimming Pool", "Hotel Gym",
                "Hotel Concierge Service", "Hotel Spa", "Hotel 7/24 Room Service"};

        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(columnsOfHotelTable.length, this.hotelManager.fetchAllHotels());
        this.createTable(this.default_table_hotel, this.tbl_hotel, columnsOfHotelTable, hotelList);
    }

    private void initializeTypesTable(){
        Object[] columnsOfTypesTable = {"Type ID", "Hotel Name", "Hotel Types"};

        ArrayList<Object[]> typesList = this.typeManager.getForTable(columnsOfTypesTable.length, this.typeManager.fetchAllTypes());
        this.createTable(this.default_table_types, this.tbl_types, columnsOfTypesTable, typesList);
    }

    private void initializeSeasonsTable(){
        Object[] columnsOfSeasonsTable = {"Season ID", "Hotel Name", "Season Start", "Season End", "Season Name", "Season Rate"};

        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(columnsOfSeasonsTable.length, this.seasonManager.fetchAllSeasons());
        this.createTable(this.default_table_season, this.tbl_season, columnsOfSeasonsTable, seasonList);
    }

    private void initializeRoomsTable(){
        Object[] columnsOfRoomsTable = {"Room ID", "Hotel Name", "Room Boarding Type", "Room Season",
                "Room Adult Price", "Room Child Price", "Room Type","Room Bed Count", "Room TV", "Room Minibar",
                "Room Gaming Console", "Room Square Footage", "Room Safe", "Room Projection", "Room Stock"};

        ArrayList<Object[]> roomList = this.roomManager.getForTable(columnsOfRoomsTable.length, this.roomManager.fetchAllRooms());
        this.createTable(this.default_table_rooms, this.tbl_rooms, columnsOfRoomsTable, roomList);
    }

    private void initializeReservationsTable(){
        Object[] columnsOfReservationsTable = {"Reservation ID", "Room", "Start Date", "End Date", "Guest Name", "Phone", "Total Price"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(columnsOfReservationsTable.length, this.reservationManager.fetchAllReservations());

        this.createTable(this.default_table_reservations, this.tbl_reservations, columnsOfReservationsTable, reservationList);
    }
}

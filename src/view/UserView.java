package view;

import business.HotelManager;
import business.UserManager;
import entities.Hotel;
import entities.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserView extends Layout{
    private DefaultTableModel default_table_hotel = new DefaultTableModel();
    private HotelManager hotelManager;
    private Hotel hotel;
    private User user;
    private UserManager userManager;
    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JScrollPane scl_hotel;
    private JButton btn_addhotel;

    public UserView(User user) {
        this.hotelManager = new HotelManager();
        this.user = user;
        this.add(container);
        layoutStart((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(),(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()));

        if (this.user == null){
            dispose();
        }

        this.lbl_welcome.setText("Welcome, " + this.user.getUser_name());

        initializeHotelTable();

        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

        btn_addhotel.addActionListener(e -> {
            HotelAddMenu hotelAddMenu = new HotelAddMenu();
        });
    }

    private void initializeHotelTable(){
        Object[] columnsOfHotelTable = {"Hotel ID", "Hotel Name", "Hotel City", "Hotel E-Mail", "Hotel Phone Number",
                "Hotel Star", "Hotel Free Parking", "Hotel Free Wifi", "Hotel Swimming Pool", "Hotel Gym",
                "Hotel Concierge Service", "Hotel Spa", "Hotel 7/24 Room Service"};

        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(columnsOfHotelTable.length);
        this.createTable(this.default_table_hotel, this.tbl_hotel, columnsOfHotelTable, hotelList);
    }
}

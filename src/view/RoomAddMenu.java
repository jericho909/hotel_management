package view;

import business.HotelManager;
import business.RoomManager;
import business.TypeManager;
import core.ComboItem;
import entities.Hotel;
import entities.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomAddMenu extends Layout{
    private JPanel container;
    private JComboBox cmb_hotels;
    private JComboBox cmb_hoteltypes;
    private JComboBox cmb_hotelseasons;
    private JTextField fld_adultprice;
    private JTextField fld_childprice;
    private JTextField fld_bedcount;
    private JCheckBox check_tv;
    private JCheckBox check_minibar;
    private JCheckBox check_console;
    private JTextField fld_squarefoot;
    private JCheckBox check_safe;
    private JCheckBox check_projection;
    private JTextField fld_stock;
    private JButton btn_save;
    private JLabel lbl_hotel;
    private JLabel lbl_hotel_type;
    private JLabel lbl_season;
    private JLabel lbl_adultprice;
    private JLabel lbl_childprice;
    private JLabel lbl_roomname;
    private JLabel lbl_bedcount;
    private JLabel lbl_tv;
    private JLabel lbl_minibar;
    private JLabel lbl_console;
    private JLabel lbl_squarefoot;
    private JLabel lbl_safe;
    private JLabel lbl_projection;
    private JLabel lbl_stock;
    private JComboBox comboBox1;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private TypeManager typeManager;


    public RoomAddMenu(){
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        layoutStart((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 450, 1000);

        for (Hotel hotel: this.hotelManager.fetchAllHotels()){
            this.cmb_hotels.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
            cmb_hotels.setSelectedItem(null);
        }

        cmb_hotels.addActionListener(new ActionListener() {
            private TypeManager typeManager = new TypeManager();
            private HotelManager hotelManager = new HotelManager();

            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_hoteltypes.removeAllItems();
                String selectedHotelName = cmb_hotels.getSelectedItem().toString();
                int hotelId = this.hotelManager.queryDatabaseForId(selectedHotelName);
                ArrayList<String> boardingTypeList = this.typeManager.getTypesByHotelId(hotelId);
                for (String boardingType: boardingTypeList){
                    cmb_hoteltypes.addItem(boardingType);
                }
            }
        });


    }
}
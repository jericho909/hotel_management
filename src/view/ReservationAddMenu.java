package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.ComboItem;
import entities.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReservationAddMenu extends Layout {
    private JPanel container;
    private JComboBox cmb_hotels;
    private JComboBox cmb_room;
    private JFormattedTextField fmt_reservation_start_date;
    private JTextField fld_guestName;
    private JTextField fld_guestPhone;
    private JButton btn_createReservation;
    private JLabel lbl_hotelName;
    private JLabel lbl_room;
    private JLabel lbl_reservationStartDate;
    private JLabel lbl_reservationEndDate;
    private JFormattedTextField fmt_reservationEndDate;
    private JLabel lbl_guestName;
    private JLabel lbl_guestPhone;
    private JLabel lbl_totalPrice;
    private ReservationManager reservationManager;
    private HotelManager hotelManager;

    public ReservationAddMenu() {
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();
        this.add(container);
        layoutStart(600,750);

        for (Hotel hotel: this.hotelManager.fetchAllHotels()){
            this.cmb_hotels.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
            cmb_hotels.setSelectedItem(null);
        }
        cmb_hotels.addActionListener(new ActionListener() {
            private HotelManager hotelManager = new HotelManager();
            private RoomManager roomManager = new RoomManager();
            @Override
            public void actionPerformed(ActionEvent e) {
                cmb_room.removeAllItems();
                String selectedHotelName = cmb_hotels.getSelectedItem().toString();
                int hotelId = this.hotelManager.queryDatabaseForId(selectedHotelName);
                ArrayList<ComboItem> roomList = this.roomManager.getRoomsByHotelId(hotelId);
                for (ComboItem room: roomList){
                    cmb_room.addItem(new ComboItem(room.getKey(), room.getValue()));
                }
            }
        });
    }


}

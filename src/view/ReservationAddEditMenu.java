package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entities.Hotel;
import entities.Reservation;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class ReservationAddEditMenu extends Layout {
    private JPanel container;
    private JComboBox cmb_hotels;
    private JComboBox cmb_room;
    private JFormattedTextField fmt_reservationStartDate;
    private JTextField fld_guestName;
    private JTextField fld_guestPhone;
    private JButton btn_addEditReservation;
    private JLabel lbl_hotelName;
    private JLabel lbl_room;
    private JLabel lbl_reservationStartDate;
    private JLabel lbl_reservationEndDate;
    private JFormattedTextField fmt_reservationEndDate;
    private JLabel lbl_guestName;
    private JLabel lbl_guestPhone;
    private JLabel lbl_totalPrice;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JButton btn_calculate;
    private ReservationManager reservationManager;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private SeasonManager seasonManager;
    private Reservation reservation;

    public ReservationAddEditMenu(Reservation reservation) {
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        layoutStart(600,750);


        for (Hotel hotel: this.hotelManager.fetchAllHotels()){
            this.cmb_hotels.addItem(new ComboItem(hotel.getId(), hotel.getHotel_name()));
            cmb_hotels.setSelectedItem(null);
        }

        if (reservation != null){
            //this.cmb_hotels.setSelectedItem(this.hotelManager.getById(this.roomManager.getById(reservation.getRoom_id()).getHotel_id()).getHotel_name());
            //this.cmb_room.setSelectedItem(this.roomManager.getById(reservation.getRoom_id()).getRoom_name());
            this.fld_guestName.setText(reservation.getReservation_guest_name());
            this.fld_guestPhone.setText(reservation.getReservation_guest_phone());
            this.fmt_reservationStartDate.setText(reservation.getReservation_start_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            this.fmt_reservationEndDate.setText(reservation.getReservation_end_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        fld_child.setText("0");
        fld_adult.setText("0");

        cmb_hotels.addActionListener(new ActionListener() {
            private HotelManager hotelManager = new HotelManager();
            private RoomManager roomManager = new RoomManager();
            private SeasonManager seasonManager = new SeasonManager();
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

        btn_addEditReservation.addActionListener(e -> {
                ComboItem selectedRoom = (ComboItem) cmb_room.getSelectedItem();
                int roomSize = this.roomManager.getById(selectedRoom.getKey()).getRoom_bed_count();
                int roomStock = this.roomManager.getById(selectedRoom.getKey()).getRoom_stock();
                ArrayList<Reservation> reservationArrayList = this.reservationManager.fetchAllReservations();
                boolean result = false;
            if (Helper.emptyFieldChecker(new JTextField[]{fld_guestName, fld_guestPhone, fmt_reservationStartDate,
                    fmt_reservationEndDate}) || cmb_hotels.getSelectedItem() == null || cmb_room.getSelectedItem() == null){
                Helper.showErrorMessage("Please fill all the fields.");
            } else if (roomSize < Helper.calculateGuestNumber(new JTextField[]{fld_adult, fld_child})){
                Helper.showErrorMessage("Reservation exceeds room capacity. Room capacity: " + roomSize);
            } else if (Objects.equals(fld_adult.getText(), "0") && Objects.equals(fld_child.getText(), "0")){
                Helper.showErrorMessage("Reservation does not contain any guests.");
            } else if (0 > Helper.calculateGuestNumber(new JTextField[]{fld_adult, fld_child})
                    && Helper.calculateGuestNumber(new JTextField[]{fld_adult, fld_child}) != -1){
                Helper.showErrorMessage("Invalid entry.");
            } else if (Helper.calculateGuestNumber(new JTextField[]{fld_child, fld_adult}) == -1) {
                Helper.showErrorMessage("Guests fields cannot be empty.");
            } else if (!Helper.checkDateAvailability(LocalDate.parse(fmt_reservationStartDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    LocalDate.parse(fmt_reservationEndDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), reservationArrayList, selectedRoom.getKey())) {
                Helper.showErrorMessage("Date not available.");
            } else if (roomStock < 1) {
                Helper.showErrorMessage("Room out of stock.");
            } else {
                Double roomPriceForAdult = this.roomManager.getById(selectedRoom.getKey()).getRoom_price_adult();
                Double roomPriceForChild = this.roomManager.getById(selectedRoom.getKey()).getRoom_price_child();
                Double seasonRate = this.seasonManager.getById(this.roomManager.getById(selectedRoom.getKey()).getRoom_season_id()).getSeason_rate();
                int numberOfAdults = Integer.parseInt(fld_adult.getText());
                int numberOfChildren = Integer.parseInt(fld_child.getText());

                Double totalPrice = ((numberOfAdults * roomPriceForAdult) + (numberOfChildren * roomPriceForChild)) * seasonRate;
                if (this.reservation == null){
                    result = this.reservationManager.saveReservation(new Reservation(
                            selectedRoom.getKey(),
                            LocalDate.parse(fmt_reservationStartDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            LocalDate.parse(fmt_reservationEndDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            fld_guestName.getText(), fld_guestPhone.getText(), totalPrice));
                } else {
                    this.reservation.setRoom_id(selectedRoom.getKey());
                    this.reservation.setReservation_start_date(LocalDate.parse(fmt_reservationStartDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.reservation.setReservation_end_date(LocalDate.parse(fmt_reservationEndDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.reservation.setReservation_guest_name(fld_guestName.getText());
                    this.reservation.setReservation_guest_phone(fld_guestPhone.getText());
                    this.reservation.setReservation_total_price(totalPrice);

                    result = this.reservationManager.editReservation(this.reservation);

                    Helper.showCustomMessage("Edited reservation.", "Operation successful.");
                    dispose();
                }

                if (result) {
                    Helper.showCustomMessage("Added new reservation.", "Operation successful.");
                    this.roomManager.changeStock(roomStock - 1, selectedRoom.getKey());
                    dispose();
                }
            }

        });

        btn_calculate.addActionListener(e->{
            ComboItem selectedRoom = (ComboItem) cmb_room.getSelectedItem();
            int roomSize = this.roomManager.getById(selectedRoom.getKey()).getRoom_bed_count();
            if (roomSize < Helper.calculateGuestNumber(new JTextField[]{fld_adult, fld_child})){
                Helper.showErrorMessage("Reservation exceeds room capacity. Room capacity: " + roomSize);
            } else if (Objects.equals(fld_adult.getText(), "0") && Objects.equals(fld_child.getText(), "0")){
                Helper.showErrorMessage("Reservation does not contain any guests.");
            } else {
                Double roomPriceForAdult = this.roomManager.getById(selectedRoom.getKey()).getRoom_price_adult();
                Double roomPriceForChild = this.roomManager.getById(selectedRoom.getKey()).getRoom_price_child();
                Double seasonRate = this.seasonManager.getById(this.roomManager.getById(selectedRoom.getKey()).getRoom_season_id()).getSeason_rate();
                int numberOfAdults = Integer.parseInt(fld_adult.getText());
                int numberOfChildren = Integer.parseInt(fld_child.getText());

                Double totalPrice = ((numberOfAdults * roomPriceForAdult) + (numberOfChildren * roomPriceForChild)) * seasonRate;
                lbl_totalPrice.setText("TOTAL PRICE: " + totalPrice.toString() + "$");
            }
        });
    }


    private void createUIComponents() throws ParseException {
        this.fmt_reservationStartDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_reservationStartDate.setText("01/01/2024");
        this.fmt_reservationEndDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fmt_reservationEndDate.setText("02/01/2024");
    }
}

package view;

import entities.User;

import javax.swing.*;

public class AdminView extends Layout {

    private User user;
    private JPanel container;
    private JPanel pnl_header;
    private JButton btn_logout;
    private JLabel lbl_welcome;
    private JPanel pnl_main;

    public AdminView(User user) {
        this.user = user;
        this.add(container);
        layoutStart(500,500);

        if (user == null){
            dispose();
        }
    }
}

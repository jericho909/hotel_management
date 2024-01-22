package view;

import javax.swing.*;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JLabel lbl_welcome;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JButton btn_login;

    public LoginView() {
        this.add(container);
        layoutStart(400,400);
    }
}

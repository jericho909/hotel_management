package view;

import business.UserManager;
import core.Helper;
import entities.User;

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
    private UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        layoutStart(400,400);
        btn_login.addActionListener(e -> {
            if (Helper.emptyFieldChecker(new JTextField[]{this.fld_username, this.fld_password})){
                Helper.showErrorMessage("Please fill the empty fields.");
            } else {
                User loginUser = this.userManager.fetchUserWithLoginInfo(this.fld_username.getText(), this.fld_password.getText());
                //System.out.println(loginUser.toString());
                if (loginUser == null){
                    Helper.showErrorMessage("Cannot find user.");
                } else {
                    if (loginUser.getUser_role().equals("admin")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else {
                        UserView userView = new UserView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}

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
        //need interaction with the database, so we start the manager item
        this.userManager = new UserManager();
        this.add(container);
        layoutStart(400,400);
        //login button actions
        btn_login.addActionListener(e -> {
            //checking if the username and password fields for null values
            if (Helper.emptyFieldChecker(new JTextField[]{this.fld_username, this.fld_password})){
                Helper.showErrorMessage("Please fill the empty fields.");
            } else {
                //go to the database, fetch the user with the username and password, if null show the user error message
                User loginUser = this.userManager.fetchUserWithLoginInfo(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null){
                    Helper.showErrorMessage("Cannot find user.");
                } else {
                    //if the user role is admin show the user admin page
                    if (loginUser.getUser_role().equals("admin")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                    } else {
                        //if not show the user the normal view
                        UserView userView = new UserView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}

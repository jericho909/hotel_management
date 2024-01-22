package view;

import business.UserManager;
import core.Helper;
import entities.User;

import javax.swing.*;
import java.util.Objects;

public class UserAddEditMenu extends Layout {
    private JPanel container;
    private JLabel lbl_header;
    private JTextField fld_username;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JPasswordField fld_password;
    private JLabel lbl_role;
    private JComboBox cmb_role_options;
    private JButton btn_submit;
    private UserManager userManager;
    private User user;

    public UserAddEditMenu(User user){
        this.userManager = new UserManager();
        this.user = user;
        this.add(container);
        this.layoutStart(500,500);

        if (user != null){
            this.fld_username.setText(user.getUser_name());
            this.fld_password.setText(user.getUser_password());
            if (user.getUser_role().equals("admin")){
                cmb_role_options.setSelectedItem("Administrator");
            } else {
                cmb_role_options.setSelectedItem("User");
            }
        }

        btn_submit.addActionListener(e->{
            if (Helper.emptyFieldChecker(new JTextField[]{this.fld_username, this.fld_password})){
                Helper.showErrorMessage("Please fill out all the fields.");
            } else {
                boolean result = false;
                boolean updateIsValid = false;
                String userRole;
                if (cmb_role_options.getSelectedItem().equals("Administrator")){
                    userRole = "admin";
                } else {
                    userRole = "user";
                }


                if (this.user == null){
                    System.out.println("User does not exist. Adding");
                    result = this.userManager.saveUser(new User(fld_username.getText(), fld_password.getText(),userRole));
                    result = true;
                } else {
                    System.out.println("User exists. Editing");

                    this.user.setUser_name(fld_username.getText());
                    this.user.setUser_password(fld_password.getText());
                    result = this.userManager.editUser(this.user);
                    updateIsValid = true;
                    Helper.showCustomMessage("Edited user.", "Operation successful.");
                    dispose();
                }

                if (result){
                    Helper.showCustomMessage("Added user.", "Operation successful.");
                    dispose();
                }
            }
        });


    }
}

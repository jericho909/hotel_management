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

        //if the user isn't null, this is an edit operation so we fill the fields with the selected users info for better UX.
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
            if (Helper.emptyFieldChecker(new JTextField[]{this.fld_username, this.fld_password})){ //empty field checks
                Helper.showErrorMessage("Please fill out all the fields.");
            } else {
                boolean result = false;
                boolean updateIsValid = false;
                String userRole;
                if (cmb_role_options.getSelectedItem().equals("Administrator")){ //box selection value is different from the value we save to the database, we show the user the longer version for better UX.
                    userRole = "admin";
                } else {
                    userRole = "user";
                }


                if (this.user == null){
                    //if user is null, this is a new user so we call the save method
                    result = this.userManager.saveUser(new User(fld_username.getText(), fld_password.getText(),userRole));
                    result = true;
                } else {
                    //not null, user exists, call the edit method
                    this.user.setUser_name(fld_username.getText());
                    this.user.setUser_password(fld_password.getText());
                    result = this.userManager.editUser(this.user);
                    updateIsValid = true;
                    Helper.showCustomMessage("Edited user.", "Operation successful.");
                    dispose();
                }

                if (result){
                    //if result is true, this is a save action, show the relevant message.
                    Helper.showCustomMessage("Added user.", "Operation successful.");
                    dispose();
                }
            }
        });


    }
}

package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    public Pane loginPaneID;
    public JFXTextField loginUserNameTxtID;
    public JFXPasswordField loginPasswordTxtID;
    public ImageView passwordIconID;
    public Pane signUpPaneID;
    public JFXTextField signUpUserNameTxtID;
    public JFXPasswordField signUpPasswordTxtID;
    public ImageView signUpTopasswordIconID;
    public JFXTextField signUpEmailTxtID;
    public JFXTextField signUpPhoneNumberTxtID;
    public Label loginNotifierID;
    public Label signUpNotficationID;

    public static String username, password, email;
    public static ArrayList<User> loggedInUser = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<User>();
    public ImageView loginPasswordShow;
    public ImageView loginPasswordUnShow;

    public void initialize() {
        loginNotifierID.setVisible(false);
    }

    public void cancelOnClick(MouseEvent event) {}


    public void loginBtnOnAction(ActionEvent event) {
        username = loginUserNameTxtID.getText();
        password = loginPasswordTxtID.getText();
        boolean login = false;

        for (User user : users) {
            if (user.name.equalsIgnoreCase(username) && user.password.equalsIgnoreCase(password)) {
                login = true;
                loggedInUser.add(user);
                System.out.println(user.name);
                break;
            }
        }
        if (login) {
            changeWindow();
        } else {
            loginNotifierID.setVisible(true);
        }
    }


    public void loginSignUpLinkOnAction(ActionEvent event) {
        loginPaneID.setVisible(false);
        signUpPaneID.setVisible(true);


    }


    public void showPasswordIconClick(MouseEvent event) {}


    public void signUpBtnOnAction(ActionEvent event) {
        if (!signUpUserNameTxtID.getText().equalsIgnoreCase("")
                && !signUpPasswordTxtID.getText().equalsIgnoreCase("")
                && !signUpEmailTxtID.getText().equalsIgnoreCase("")
                && !signUpPhoneNumberTxtID.getText().equalsIgnoreCase("")) {

            if (checkUser(signUpUserNameTxtID.getText())) {
                if (checkEmail(signUpEmailTxtID.getText())) {
                    User newUser = new User();
                    newUser.name = signUpUserNameTxtID.getText();
                    newUser.password = signUpPasswordTxtID.getText();
                    newUser.email = signUpEmailTxtID.getText();
                    newUser.phoneNo = signUpPhoneNumberTxtID.getText();

                    users.add(newUser);
                    signUpNotficationID.setVisible(false);
                    makeDefault();


                }
            }
        }
    }


    public void signUpToSignInLinkOnAction(ActionEvent event) {
        signUpPaneID.setVisible(false);
        loginPaneID.setVisible(true);

    }

    private boolean checkUser(String username) {
        for (User user : users) {
            if (user.name.equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }


    private boolean checkEmail(String email) {
        for (User user : users) {
            if (user.email.equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    private void makeDefault() {
        signUpUserNameTxtID.setText("");
        signUpPasswordTxtID.setText("");
        signUpEmailTxtID.setText("");
        signUpPhoneNumberTxtID.setText("");
    }

    private void changeWindow() {
        try {
            Stage stage = (Stage) loginUserNameTxtID.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/chatUi.fxml"));

            stage.setScene(new Scene(root, 420, 600));
            stage.setTitle(username + "");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

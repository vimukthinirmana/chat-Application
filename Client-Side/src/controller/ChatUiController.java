package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatUiController {
    public ImageView addUsers;
    public Label clientNameID;
    public Circle showProPic;
    public ImageView backBtnID;
    public Pane paneClientChatID;
    public VBox vBoxID;
    public TextField msgFieldID;
    public Pane paneClientDataUiID;
    public JFXTextField userNameTxtID;
    public JFXPasswordField PasswordTxtID;
    public ImageView passwordIconID;
    public JFXTextField EmailTxtID;
    public JFXTextField phoneNoTxtID;
    public ImageView proImage;


    public void initialize() {
        backBtnID.setVisible(false);
    }

    @FXML
    void addNewStage(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login.fxml"))));
        stage.setTitle("Authentication"); stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void backBtnClicked(MouseEvent event) {
        paneClientDataUiID.setVisible(false);
        paneClientChatID.setVisible(true);
        backBtnID.setVisible(false);
    }

    @FXML
    void cameraIconMouseClicked(MouseEvent event) {

    }

    @FXML
    void chooseImageButton(MouseEvent event) {

    }

    @FXML
    void faceIconMouseClicked(MouseEvent event) {

    }

    @FXML
    void handleProfileBtn(MouseEvent event) {
        paneClientChatID.setVisible(false);
        paneClientDataUiID.setVisible(true);
        backBtnID.setVisible(true);
    }

    @FXML
    void handleSendEvent(MouseEvent event) {

    }

    @FXML
    void showPasswordIconClick(MouseEvent event) {

    }
    

    public void saveBtnOnAction(ActionEvent actionEvent) {

    }
}

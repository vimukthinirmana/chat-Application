package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientUiController {
    @FXML
    private Pane chatPaneID;

    @FXML
    private TextField txtFieldMsgID;

    @FXML
    private JFXButton sendBtnID;

    @FXML
    private VBox vBoxID;

    @FXML
    private JFXTextArea txtAreaID;

    @FXML
    private Pane loginPaneID;

    @FXML
    private JFXButton loginBtnId;

    @FXML
    private TextField txtNameId;


    public void initialize() {
        loginPaneID.setVisible(true);
        chatPaneID.setVisible(false);
    }

    private void initUI() {

    }

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        loginPaneID.setVisible(false);
        chatPaneID.setVisible(true);
    }

    @FXML
    void sendBtnOnAction(ActionEvent event) {

    }


}

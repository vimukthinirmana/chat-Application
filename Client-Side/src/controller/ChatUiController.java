package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.LoginController.users;

public class ChatUiController extends Thread implements Initializable {
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

    public boolean toggleChat = false, toggleProfile = false;
    public boolean saveControl = false;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    String msg = "";
    private FileChooser fileChooser;
    private File filePath;


    public void initialize(URL location, ResourceBundle resources) {
        backBtnID.setVisible(false);
        showProPic.setStroke(Color.valueOf("#90a4ae"));
        Image image;
        image = new Image("view/icon/unknown-user.png", false);
        proImage.setImage(image);

        showProPic.setFill(new ImagePattern(image));
        clientNameID.setText(LoginController.username);
        connectSocket();
    }

    public void connectSocket() {
        try {
            socket = new Socket("localhost", 3000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(LoginController.username + " " + "img" + filePath.getPath());
    }

    @FXML
    void chooseImageButton(MouseEvent event) {}

    @FXML
    void faceIconMouseClicked(MouseEvent event) {
//emoji sent option
    }

    @Override
    public void run() {
        try {
            while (true) {


                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

//                txtTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }


                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }
//======================================================================


                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(LoginController.username)) {

                        vBoxID.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> vBoxID.getChildren().addAll(hBox));


                } else {
                    //For the Text
                    text.setFill(Color.WHITE);
                    text.getStyleClass().add("message");
                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(LoginController.username + ":")) {
                        Text txtName = new Text(cmd );
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12
                    hBox.setMinHeight(50);
                    hBox.setMaxHeight(50);
                    hBox.setPrefHeight(50);
                    hBox.setFillHeight(false);




                    //=================================================


                    if (!cmd.equalsIgnoreCase(LoginController.username + ":")) {
                        tempFlow.getStyleClass().add("tempFlowFlipped");
                        flow.getStyleClass().add("textFlowFlipped");
                        vBoxID.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {
                        text.setFill(Color.WHITE);
                        tempFlow.getStyleClass().add("tempFlow");
                        flow.getStyleClass().add("textFlow");

                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow);
                    }
                    hBox.getStyleClass().add("hbox");
                    Platform.runLater(() -> vBoxID.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleProfileBtn(MouseEvent event) {
        paneClientChatID.setVisible(false);
        paneClientDataUiID.setVisible(true);
        backBtnID.setVisible(true);
    }

    @FXML
    void handleSendEvent(MouseEvent event) {
        send();
        for (User user : users) {
            System.out.println(user.name);
        }
    }

    @FXML
    void showPasswordIconClick(MouseEvent event) {}


    public void saveBtnOnAction(ActionEvent actionEvent) {}


    public void send() {
        msg = msgFieldID.getText();
        writer.println(LoginController.username + ": " + msg);

       /* msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        msgRoom.appendText("Me: " + msg + "\n");*/


        msgFieldID.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }



}

package lk.ijse.Fusion.controller.Main;

import com.jfoenix.controls.JFXButton;
import com.sun.media.jfxmedia.events.PlayerEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import lk.ijse.Fusion.Util.Navigation;
import lk.ijse.Fusion.Util.Routes;
import org.apache.poi.hssf.record.formula.functions.Na;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Main implements Initializable {
    public TextField usernameTXT;
    public PasswordField passwordTXT;
    public MediaView mediaView;
    public AnchorPane MainPane;
    public MediaView bannerView;
    public MediaView mediafram2;
    public JFXButton logbtn;
    public Label lblUserName;
    public Label lblPassword;


    public void accessLogin() throws IOException {
        System.out.println(usernameTXT.getText());
        String tempUsername = usernameTXT.getText();
        String tempPassword = passwordTXT.getText();
        System.out.println(passwordTXT.getText());
        if (tempUsername.equals("prabhash") && tempPassword.equals("2001")) {

            Navigation.navigate(Routes.DASHBOARD, MainPane);
        }
        if (tempUsername.equals("stock") && tempPassword.equals("2001")) {
            Navigation.navigate(Routes.STOCKDASH, MainPane);
        }
        if (tempUsername.equals("it") && tempPassword.equals("2001")) {
            Navigation.navigate(Routes.IT_MANAGERDASH, MainPane);
        }
    }

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
        accessLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("FusionTechComputerSolutions-20230123T181011Z-001/FusionTechComputerSolutions/wow.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mediaView.setMediaPlayer(mp);
        mp.play();



    }





    public void txtLoginOnKeyReleased(KeyEvent keyEvent) throws IOException {
        validate();
        if (keyEvent.getCode()== KeyCode.ENTER) {
            Object response=validate();

            if (response instanceof TextField){
                TextField textField=(TextField) response;
                textField.requestFocus();
            }else if(response instanceof Boolean){

            }
            accessLogin();
        }
    }

    private Object validate() {
        Pattern namePattern = Pattern.compile("^[A-z ]{2,8}$"); // prabhash //2001
        Pattern passwordPatten = Pattern.compile("^[0-5 ]{4,4}$");

        if(!(namePattern.matcher(usernameTXT.getText()).matches())){
            addError(usernameTXT);
            lblUserName.setText("Enter the valid UserName");
            return usernameTXT;
        }else{
            removeError(usernameTXT);
            lblUserName.setVisible(false);

            if((!passwordPatten.matcher(passwordTXT.getText()).matches())){
                addError(passwordTXT);
                lblPassword.setText("Enter the valid Password");
                return passwordTXT;
            }else{
                removeError(passwordTXT);
                lblPassword.setVisible(false);
            }
        }
        return true;
    }

    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: lightblue");
        logbtn.setDisable(false);
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red");
        logbtn.setDisable(true);
    }


}

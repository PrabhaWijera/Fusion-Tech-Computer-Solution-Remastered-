package lk.ijse.Fusion.controller.DashBordes;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import lk.ijse.Fusion.Util.Navigation;
import lk.ijse.Fusion.Util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashHomeController {

    public AnchorPane pane;
    public Label total_customerlbl;
    public Label top_itemLbl;
    public Label username_lbl;
    public AnchorPane pane1;

    public Label timeLbl;
    public Label dateLbl;
    public MediaView mediaView;
    public Button btnItems;
    public Button stlb;
    public Button emplb;
    public Button cuslb;
    public Button suplb;
    public MediaView hrmediaView;


    public void initialize(){
        setUserName();
        setDateAndTime();
        setTime();
        setDate();
/*
        File file = new File("SuppliersDTO.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mediaView.setMediaPlayer(mp);
        mp.play();
*/
/*
       file = new File("pp.mp4");
         media = new Media(file.toURI().toString());
        MediaPlayer hr = new MediaPlayer(media);
        mp.setAutoPlay(true);
        hrmediaView.setMediaPlayer(hr);
        mp.play();*/
    }


    private void setDate() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLbl.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setTime() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY:MM:dd");
            dateLbl .setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setDateAndTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm ");
            timeLbl.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(javafx.util.Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
     private void setUserName(){
username_lbl.setText(username_lbl.getText());
    }

    public void LogOut_OnAction(ActionEvent actionEvent) throws IOException {
       Navigation.navigate(Routes.MAINPAGE,pane1);


    }

    public void Go_itemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,pane);
    }

    public void Go_ordersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDERS,pane);
    }

    public void Go_employeeOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void Go_customerOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.CUSTOMER,pane);
    }

    public void Go_suppliersOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.SUPPLIER,pane);
    }

    public void Go_stockOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.STOCK,pane);
    }


    public void Go_returnOnAction(ActionEvent actionEvent) {
    }

    public void itemEnter(MouseEvent mouseEvent) {
        btnItems.setStyle("-fx-background-color:  #bbb7fa");
    }

    public void itemExit(MouseEvent mouseEvent) {
        btnItems.setStyle("-fx-background-color:  #ced6e0");
    }

   

    public void empEnter(MouseEvent mouseEvent) {
        emplb.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void empExit(MouseEvent mouseEvent) {
        emplb.setStyle("-fx-background-color: #ced6e0");
    }


    public void cusEnter(MouseEvent mouseEvent) {
        cuslb.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void cusExit(MouseEvent mouseEvent) {
        cuslb.setStyle("-fx-background-color: #ced6e0");
    }

    public void supEntr(MouseEvent mouseEvent) {
        suplb.setStyle("-fx-background-color: #bbb7fa;");
    }

    public void supExit(MouseEvent mouseEvent) {
        suplb.setStyle("-fx-background-color: #ced6e0");
    }

    public void stEnter(MouseEvent mouseEvent) {
        stlb.setStyle("-fx-background-color:#bbb7fa ");
    }

    public void stExit(MouseEvent mouseEvent) {
        stlb.setStyle("-fx-background-color:#ced6e0 ");
    }

    public void Go_NewAccountOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SIGNUPPAGE,pane);
    }

    public void ReturnDashONClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD,pane1);
    }
}

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
import org.apache.poi.hssf.record.formula.functions.Na;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ITManagerDashHomeController {
    public Label totalCustomerLbl;
    public Label topItemLbl;
    public Label usernameLbl;
    public AnchorPane pane;
    public AnchorPane pane1;
    public Label lblDate;
    public Label lblTime;
    public Button itemBtn;
    public Button plcBtn;
    public Button cusBtn;
    public Button supBtn;
    public Button stockBtn;
    public MediaView stMedia2;
    public MediaView itmediaView;

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINPAGE,pane1);
    }

    public void Go_itemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ITEM,pane);
    }

    public void Go_ordersOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.ORDERS,pane);
    }

    public void Go_customersOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.CUSTOMER,pane);
    }

    public void Go_PaymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,pane);
    }

    public void Go_suppliersOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.SUPPLIER,pane);
    }

    public void Go_stockOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.STOCK,pane);
    }

    public void Go_deliveryOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.DELIVERY,pane);
    }

    public void initialize(){
        setTime();
        setDate();


        File file = new File("Suppliers.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer  ty = new MediaPlayer(media);
        ty.setAutoPlay(true);
        itmediaView.setMediaPlayer(ty);
        ty.play();


        file = new File("pp.mp4");
         media = new Media(file.toURI().toString());
        MediaPlayer op = new MediaPlayer(media);
        op.setAutoPlay(true);
        stMedia2.setMediaPlayer(op);
        op.play();
    }

    private void setTime() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO,e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("YYYY:MM:dd");
            lblTime .setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setDate() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    public void GoPlaceOrder(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDER,pane);
    }

    public void itemKeyEnter(MouseEvent mouseEvent) {
        itemBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void itemKeyExit(MouseEvent mouseEvent) {
        itemBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void placeEnter(MouseEvent mouseEvent) {
        plcBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void placeExit(MouseEvent mouseEvent) {
        plcBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void cusEnter(MouseEvent mouseEvent) {
        cusBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void cusExit(MouseEvent mouseEvent) {
        cusBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void supEnter(MouseEvent mouseEvent) {
        supBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void supExit(MouseEvent mouseEvent) {
        supBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void stockEnter(MouseEvent mouseEvent) {
        stockBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void exit(MouseEvent mouseEvent) {
        stockBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void ReturnDashOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.IT_MANAGERDASH,pane1);
    }
}

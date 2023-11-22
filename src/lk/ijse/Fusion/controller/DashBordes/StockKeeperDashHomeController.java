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

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockKeeperDashHomeController {
    public Label instockLbl;
    public Label soldItemLbl;
    public Label userNameLbl;
    public AnchorPane pane;
    public AnchorPane pane1;
    public Label datelbl;
    public Label Itemlbl;
    public Button itemBtn;
    public Button stockBtn;
    public MediaView stMediaView;


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINPAGE,pane1);
    }

    public void Go_itemOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.ITEM,pane);
    }

    public void Go_StockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCK,pane);
    }



    public void initialize(){
        setDate();
        setTime();


        File file = new File("SuppliersDTO.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        stMediaView.setMediaPlayer(mp);
        mp.play();


    }

    private void setTime() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
            Itemlbl.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    private void setDate() {
        Timeline timeline=new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
            datelbl.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void ItemEnter(MouseEvent mouseEvent) {
        itemBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void itemExit(MouseEvent mouseEvent) {
        itemBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void stockEnter(MouseEvent mouseEvent) {
        stockBtn.setStyle("-fx-background-color: #bbb7fa ");
    }

    public void stockExit(MouseEvent mouseEvent) {
        stockBtn.setStyle("-fx-background-color: #ced6e0");
    }

    public void ReturnDashOnClick(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STOCKDASH,pane1);
    }
}

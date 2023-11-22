package lk.ijse.Fusion.controller.Attends;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.Util.Navigation;
import lk.ijse.Fusion.Util.Routes;
import lk.ijse.Fusion.dto.AttendenceDTO;
import lk.ijse.Fusion.dao.custom.impl.AttendsDAOimpl;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.AttendsService;
import lk.ijse.Fusion.service.custom.impl.AttendsServiceimpl;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendsController extends AttendsDAOimpl {


    public TextField inTimeTxt;
    public TextField outTimeTxt;
    public TextField dateTxt;
    public TextField employeestetusTxt;

    public TableView TblfullAttends;
    public TableColumn ColattendId;
    public TableColumn Coldate;
    public TableColumn Col_inTime;
    public TableColumn Col_outTime;
    public TableColumn Colstetus;
    public TextField attendsId_Txt;
    public AnchorPane pane;
    public AttendsService attendsService;


   private final String  AttendID  =  attendsId_Txt.getText();
   private final int  AttendDate =Integer.parseInt(dateTxt.getText());
   private final String InTime = inTimeTxt.getText();
   private final String OutTime = outTimeTxt.getText();

 private final AttendenceDTO attendencedto = new AttendenceDTO(AttendID,AttendDate,InTime,OutTime );
    public AnchorPane pane1;
    public AnchorPane CusPane;
    public TableView tblCus;
    public TableColumn ColCusID;
    public TableColumn ColNic;
    public TableColumn ColName;
    public TableColumn ColPhone;
    public TableColumn ColEmail;
    public TableColumn ColAddress;
    public TextField phoneTxt;
    public TextField nicTxt;
    public TextField emailTxt;
    public TextField CustomerNameTxt;
    public TextField addressTxt;
    public TextField CustomerlDTxt;
    public Button btnAdd;


    public AttendsController(Connection connection) {
        super(connection);
    }



    public void initialize() throws SQLException, ClassNotFoundException {
        attendsService= ServiceFactory.getInstance().getService(ServiceType.ATTENDS);
        ColattendId.setCellValueFactory(new PropertyValueFactory("AttendID"));
        Coldate.setCellValueFactory(new PropertyValueFactory("AttendDate"));
        Col_inTime.setCellValueFactory(new PropertyValueFactory("InTime"));
        Col_outTime.setCellValueFactory(new PropertyValueFactory("OutTime"));

        try {
            loadAllAttends();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }




    @FXML
     public void AddOnAction(ActionEvent actionEvent) throws IOException {
        String  AttendID  =  attendsId_Txt.getText();
        int  AttendDate =Integer.parseInt(dateTxt.getText());
        String InTime = inTimeTxt.getText();
        String OutTime = outTimeTxt.getText();

        AttendenceDTO attendencedto = new AttendenceDTO(AttendID,AttendDate,InTime,OutTime );
        AttendsServiceimpl attendsServiceimpl=ServiceFactory.getInstance().getService(ServiceType.ATTENDS);
        try {
          attendsService.saveAttends(attendencedto);
            // boolean isAdded = AttendsDAOimpl.addAttends(attendencedto);
            if (attendsServiceimpl!=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attends Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        attendsId_Txt.clear();dateTxt.clear();inTimeTxt.clear();outTimeTxt.clear();
    }


    private void fillData(AttendenceDTO attendencedto) {
        attendsId_Txt.setText(attendencedto.getAttendID());
        dateTxt.setText(String.valueOf(attendencedto.getAttendDate()));
        inTimeTxt.setText(attendencedto.getInTime());
        outTimeTxt.setText(attendencedto.getOutTime());

    }

    public void SearchOnAction(ActionEvent actionEvent) {
      // String AttendID =  attendsId_Txt.getText();
        try {
            AttendenceDTO attendencedto = AttendsDAOimpl.search(AttendID);
            attendsService.SearchAttends(AttendID);
            if (attendencedto != null) {
                fillData(attendencedto);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        String  AttendID  =  attendsId_Txt.getText();
        int  AttendDate = Integer.parseInt(dateTxt.getText());
        String InTime = inTimeTxt.getText();
        String OutTime = outTimeTxt.getText();


        AttendenceDTO attendencedto = new AttendenceDTO(AttendID,AttendDate,InTime,OutTime );
        try {
            attendsService.updateAttends(attendencedto);


            if (attendencedto != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attends Update!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        attendsId_Txt.clear();dateTxt.clear();inTimeTxt.clear();outTimeTxt.clear();

    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String  AttendID  =  attendsId_Txt.getText();
        int  AttendDate = Integer.parseInt( dateTxt.getText());
        String InTime =  inTimeTxt.getText();
        String OutTime = outTimeTxt.getText();

        AttendenceDTO attendenceDTO = new AttendenceDTO(AttendID,AttendDate,InTime,OutTime);

        attendsService.DeleteAttends(AttendID);


        if (attendencedto != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Attends Delete!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

        attendsId_Txt.clear();dateTxt.clear();inTimeTxt.clear();outTimeTxt.clear();

    }

    public void ReloadOnAction(ActionEvent actionEvent) {

    }


    public void Back_onAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void nicKeyOnRelases(KeyEvent keyEvent) {
    }

    public void phnKeyOnRelases(KeyEvent keyEvent) {
    }

    public void emailKeyRelease(KeyEvent keyEvent) {
    }

    public void cusNameKeyOnRelese(KeyEvent keyEvent) {
    }

    public void addressKeyRelese(KeyEvent keyEvent) {
    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
    }

    public void cusID_OnKeyRelease(KeyEvent keyEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void PrintOnAction(ActionEvent actionEvent) {
    }
}

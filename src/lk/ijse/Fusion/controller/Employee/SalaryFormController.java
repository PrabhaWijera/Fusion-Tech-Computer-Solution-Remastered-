package lk.ijse.Fusion.controller.Employee;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Util.Navigation;
import lk.ijse.Fusion.Util.Routes;
import lk.ijse.Fusion.dao.custom.impl.SalaryDAOimpl;
import lk.ijse.Fusion.dto.SalaryDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.SalaryService;

import java.io.IOException;
import java.sql.SQLException;

public class SalaryFormController {
    public TextField amountTxt;
    public TextField employeeIdTxt;
    public TextField SalaryIdtxt;
    public TextField salaryMethodTxt;
    public TextField txtDetail;
    public AnchorPane pane;
    public SalaryService salaryService;


public void initialize(){

}
    public void deleteOnAction(ActionEvent actionEvent) {
       String  SalaryID  =  SalaryIdtxt.getText();
        String  EmployeeID =employeeIdTxt.getText();
        double SalaryAmount = Double.parseDouble(amountTxt.getText());
        String SalaryMethod = salaryMethodTxt.getText();
        String  Details = txtDetail.getText();

        SalaryDTO salarydto = new SalaryDTO(SalaryID,EmployeeID,SalaryAmount,SalaryMethod,Details);
        boolean b = salaryService.DeleteSalary(SalaryID);
        if (salarydto != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "SalaryDTO Delete!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String  SalaryID  =  SalaryIdtxt.getText();
        String  EmployeeID =employeeIdTxt.getText();
        double SalaryAmount = Double.parseDouble(amountTxt.getText());
        String SalaryMethod = salaryMethodTxt.getText();
        String  Details = txtDetail.getText();

       SalaryDTO salarydto = new SalaryDTO(SalaryID,EmployeeID,SalaryAmount,SalaryMethod,Details);
        if (salarydto !=null) {
            new Alert(Alert.AlertType.CONFIRMATION, "SalaryDTO Update!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    public void addOnAction(ActionEvent actionEvent) {
        String  SalaryID  =  SalaryIdtxt.getText();
        String  EmployeeID =employeeIdTxt.getText();
        double SalaryAmount = Double.parseDouble(amountTxt.getText());
        String SalaryMethod = salaryMethodTxt.getText();
        String  Details = txtDetail.getText();


        SalaryDTO salarydto = new SalaryDTO(SalaryID,EmployeeID,SalaryAmount,SalaryMethod,Details);
       try{
           salaryService.saveSalary(salarydto);
           if (salarydto !=null) {
               new Alert(Alert.AlertType.CONFIRMATION, "SalaryDTO Added!").show();
           } else {
               new Alert(Alert.AlertType.WARNING, "Something happened!").show();
           }
           } catch (SQLException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
    }

    public void Back_OnAction(ActionEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }
}

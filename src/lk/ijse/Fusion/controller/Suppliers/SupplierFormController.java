package lk.ijse.Fusion.controller.Suppliers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.Fusion.dto.SuppliersDTO;
import lk.ijse.Fusion.dao.custom.impl.SuppliersDAOimpl;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.SuppliersService;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class SupplierFormController {

    public TextField priceTxt;
    public TextField emailTxt;
    public TextField amountTxt;
    public TextField phoneNoTxt;
    public TextField supplierNamTxt;
    public TextField supplierIdTxt;
    public TextField itemTxt;
    public Button addbtn;
    public SuppliersService suppliersService;



    public void initialize() throws SQLException, ClassNotFoundException {
        suppliersService= ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
    }

    public void addOnAction(ActionEvent actionEvent) {
      String SupplierID = supplierIdTxt.getText();
        String SupplierName = supplierNamTxt.getText();
        String SupplierEmail = emailTxt.getText();
        String SupplierContact = phoneNoTxt.getText();
        double Price = Double.parseDouble(priceTxt.getText());
        String ItemCode = itemTxt.getText();
        String Amount=amountTxt.getText();

        SuppliersDTO suppliersdto = new SuppliersDTO(SupplierID, SupplierName,SupplierEmail,SupplierContact,Price,ItemCode,Amount);
        try {
            //suppliersService.saveSuppliers(suppliersdto);
            boolean c = suppliersService.DeleteSuppliers(SupplierID);

            if (suppliersdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        supplierIdTxt.clear();supplierNamTxt.clear();emailTxt.clear();phoneNoTxt.clear();priceTxt.clear();itemTxt.clear();amountTxt.clear();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String SupplierID = supplierIdTxt.getText();
        String SupplierName = supplierNamTxt.getText();
        String SupplierEmail = emailTxt.getText();
        String SupplierContact = phoneNoTxt.getText();
        double Price = Double.parseDouble(priceTxt.getText());
        String ItemCode = itemTxt.getText();
        String Amount=amountTxt.getText();
       SuppliersDTO suppliersdto = new SuppliersDTO(SupplierID, SupplierName,SupplierEmail,SupplierContact,Price,ItemCode,Amount);

        try {
            suppliersService.UpdateSuppliers(suppliersdto);
            if (suppliersdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        supplierIdTxt.clear();supplierNamTxt.clear();emailTxt.clear();phoneNoTxt.clear();priceTxt.clear();itemTxt.clear();amountTxt.clear();

    }

    public void deleeteeOnAction(ActionEvent actionEvent) {

        String SupplierID = supplierIdTxt.getText();
        String SupplierName = supplierNamTxt.getText();
        String SupplierEmail = emailTxt.getText();
        String SupplierContact = phoneNoTxt.getText();
        double Price = Double.parseDouble(priceTxt.getText());
        String ItemCode = itemTxt.getText();
        String Amount=amountTxt.getText();
       SuppliersDTO suppliersdto = new SuppliersDTO(SupplierID, SupplierName,SupplierEmail,SupplierContact,Price,ItemCode,Amount);

        try {
            suppliersService.DeleteSuppliers(SupplierID);
            if (suppliersdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        supplierIdTxt.clear();supplierNamTxt.clear();emailTxt.clear();phoneNoTxt.clear();priceTxt.clear();itemTxt.clear();amountTxt.clear();


    }

    public void SearchOnAction(ActionEvent actionEvent) {
        String SupplierID = supplierIdTxt.getText();
        try {
            suppliersService.findBySupplierId(SupplierID);
            SuppliersDTO suppliersdto = SuppliersDAOimpl.search(SupplierID);
            if (suppliersdto != null) {
                fillData(suppliersdto);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(SuppliersDTO suppliersdto) {
        supplierIdTxt.setText(suppliersdto.getSupplierID());
        supplierNamTxt.setText(suppliersdto.getSupplierName());
        emailTxt.setText(suppliersdto.getSupplierEmail());
        phoneNoTxt.setText(suppliersdto.getSupplierContact());
        priceTxt.setText(String.valueOf(suppliersdto.getPrice()));
        itemTxt.setText(suppliersdto.getItemCode());
        amountTxt.setText(suppliersdto.getAmount());
    }

    public void supIdKyOnRelese(KeyEvent keyEvent) {

    }

    public void supnameKeyOnRelese(KeyEvent keyEvent) {
      /*  Pattern nameptn=Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        boolean match=nameptn.matcher(supplierNamTxt.getText()).matches();
        if(match){
            supplierNamTxt.setStyle("-fx-border-color: lightgreen");
            addbtn.setDisable(false);
        }else{
            supplierNamTxt.setStyle("-fx-border-color: red");
            addbtn.setDisable(true);
        }*/
    }

    public void contactKeyOnRelese(KeyEvent keyEvent) {
    }

    public void supemailKeyOnRelese(KeyEvent keyEvent) {
   /*     Pattern emailptn=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

        boolean match=emailptn.matcher(emailTxt.getText()).matches();
        if(match){
            emailTxt.setStyle("-fx-border-color: lightgreen");
            addbtn.setDisable(false);
        }else{
            emailTxt.setStyle("-fx-border-color: red");
            addbtn.setDisable(true);
        }*/
    }

    public void amountKeyOnRelese(KeyEvent keyEvent) {
    }

    public void priceKeyOnRelese(KeyEvent keyEvent) {

    }

    public void itemCodeKeyOnRelese(KeyEvent keyEvent) {
    /*    Pattern idpattern=Pattern.compile("^(C00-)[0-9]{3,5}$");
        boolean match=idpattern.matcher(itemTxt.getText()).matches();
        if(match){
            itemTxt.setStyle("-fx-border-color: lightgreen");
            addbtn.setDisable(false);
        }else{
            itemTxt.setStyle("-fx-border-color: red");
            addbtn.setDisable(true);

        }*/
    }

}

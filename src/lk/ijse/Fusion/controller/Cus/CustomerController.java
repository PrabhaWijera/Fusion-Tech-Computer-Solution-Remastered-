package lk.ijse.Fusion.controller.Cus;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.impl.CustomerDAOimpl;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.CustomerService;
import lk.ijse.Fusion.service.custom.impl.CustomerServiceimpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import javax.sql.rowset.RowSetWarning;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CustomerController  {



    @FXML
    public TextField CustomerlDTxt;

    public AnchorPane pane;
    @FXML
    public TextField addressTxt;
    @FXML
    public TableView<CustomerDTO> tblCus;

    @FXML
    public TextField CustomerNameTxt;

    @FXML

    public TextField nicTxt;

    @FXML
    public TextField phoneTxt;

    public CustomerService customerService;

    @FXML
    public TextField emailTxt;
    public TableColumn ColName;
    public TableColumn ColAddress;
    public TableColumn ColPhone;
    public TableColumn ColCusID;
    public TableColumn ColNic;
    public TableColumn ColEmail;
    public AnchorPane CusPane;
    public Button btnAdd;
   private String CustomerID;
   private String CustomerNIC;
   private String CustomerName;
   private String Address;
   private String PhoneNumber;
   private String Email;



    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String id = CustomerlDTxt.getText();
        try {
            CustomerDTO customerdto = CustomerDAOimpl.search(id);
            if (customerdto != null) {
                fillData(customerdto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(CustomerDTO customerdto) {
        CustomerlDTxt.setText(customerdto.getCustomerID());
        nicTxt.setText(customerdto.getCustomerNIC());
        CustomerNameTxt.setText(customerdto.getCustomerName());
        addressTxt.setText(customerdto.getAddress());
        phoneTxt.setText(customerdto.getPhoneNumber());
        emailTxt.setText(customerdto.getEmail());
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerID = CustomerlDTxt.getText();
        CustomerNIC = nicTxt.getText();
        CustomerName = CustomerNameTxt.getText();
        Address = addressTxt.getText();
        PhoneNumber = phoneTxt.getText();
        Email = emailTxt.getText();

        CustomerDTO customerdto = new CustomerDTO(CustomerID, CustomerNIC, CustomerName, Address, PhoneNumber, Email);
    //    CustomerServiceimpl customerServiceimpl = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        customerService.UpdateCustomer(customerdto);
   // boolean b1=customerServiceimpl.UpdateCustomer(customerdto);
        if (customerdto!= null){
            new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Update!").show();
        } else{
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
        CustomerlDTxt.clear();
            nicTxt.clear();
            CustomerNameTxt.clear();
            addressTxt.clear();
            phoneTxt.clear();
            emailTxt.clear();

    }


    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
         CustomerID = CustomerlDTxt.getText();
         CustomerNIC = nicTxt.getText();
         CustomerName = CustomerNameTxt.getText();
         Address = addressTxt.getText();
         PhoneNumber = phoneTxt.getText();
         Email = emailTxt.getText();

        CustomerDTO customerdto = new CustomerDTO(CustomerID, CustomerNIC, CustomerName, Address, PhoneNumber, Email);
        CustomerServiceimpl css=ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);;
        try {
            //service.DeleteCustomer(CustomerID);
      boolean c = customerService.DeleteCustomer(CustomerID);
            if (c!=true) {
                new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CustomerlDTxt.clear();nicTxt.clear();CustomerNameTxt.clear();addressTxt.clear();phoneTxt.clear();emailTxt.clear();
    }


    public void searchOnAction(ActionEvent actionEvent) {
         CustomerID = CustomerlDTxt.getText();
        try {
           // CustomerDTO customerdto = CustomerDAOimpl.search(CustomerID);
          CustomerDTO customerdto=customerService.findByCustomerId(CustomerID);
            if (customerdto !=null) {
                fillData(customerdto);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerID = CustomerlDTxt.getText();
         CustomerNIC = nicTxt.getText();
         CustomerName = CustomerNameTxt.getText();
         Address = addressTxt.getText();
         PhoneNumber = phoneTxt.getText();
         Email = emailTxt.getText();

        CustomerDTO customerdto = new CustomerDTO(CustomerID, CustomerNIC, CustomerName, Address, PhoneNumber, Email);
        CustomerServiceimpl css=ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        try {
            customerService.saveCustomer(customerdto);
            if (css!=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        CustomerlDTxt.clear();nicTxt.clear();CustomerNameTxt.clear();addressTxt.clear();phoneTxt.clear();emailTxt.clear();


    }

   public void initialize() throws SQLException, ClassNotFoundException {
        customerService= ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);//initialze method ekkk hadnne eka wenne fxml file load krna eka vithrai, anika object hdnne nh but dao wla object ekk hadana nisa api constructor eke hdnava
        ColCusID.setCellValueFactory(new PropertyValueFactory("CustomerID"));
       ColNic.setCellValueFactory(new PropertyValueFactory("CustomerNIC"));
        ColName.setCellValueFactory(new PropertyValueFactory("CustomerName"));
        ColAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        ColPhone.setCellValueFactory(new PropertyValueFactory("PhoneNumber"));
       ColEmail.setCellValueFactory(new PropertyValueFactory("Email"));
        try {
            loadAllCustomer();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    private void loadAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("SELECT * FROM  Customer");
        ObservableList<CustomerDTO> observableList = FXCollections.observableArrayList();
        while(resultSet.next()){
                observableList.add(
                        new CustomerDTO(
                                resultSet.getString("CustomerID"),
                                resultSet.getString("CustomerNIC"),
                                resultSet.getString("CustomerName"),
                                resultSet.getString("Address"),
                                resultSet.getString("PhoneNumber"),
                                resultSet.getString("Email")
                        )
                    );
        }
        tblCus.setItems(observableList);
    }


    public void nicKeyOnRelases(KeyEvent keyEvent) {
        Pattern nicPtn=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");

        boolean match=nicPtn.matcher(nicTxt.getText()).matches();
        if(match){
            nicTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            nicTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);
        }
    }

    public void phnKeyOnRelases(KeyEvent keyEvent) {
        Pattern phonePtn=Pattern.compile("^[0-9]{10}$");

        boolean match=phonePtn.matcher(phoneTxt.getText()).matches();
        if(match){
            phoneTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            phoneTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);
        }
    }

    public void emailKeyRelease(KeyEvent keyEvent) {
        Pattern emailptn=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

        boolean match=emailptn.matcher(emailTxt.getText()).matches();
        if(match){
            emailTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            emailTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);
        }
    }

    public void cusNameKeyOnRelese(KeyEvent keyEvent) {
        Pattern nameptn=Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        boolean match=nameptn.matcher(CustomerNameTxt.getText()).matches();
        if(match){
            CustomerNameTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            CustomerNameTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);
        }
    }

    public void addressKeyRelese(KeyEvent keyEvent) {
/*
        Pattern addressptn=Pattern.compile("^[^,\\\\]+[,\\\\\\s]+(.+?)\\s*(\\d{5})?$");

        boolean match=addressptn.matcher(addressTxt.getText()).matches();
        if(match){
            addressTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            addressTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);
        }
*/
    }

    public void cusID_OnKeyRelease(KeyEvent keyEvent) {
        Pattern idpattern=Pattern.compile("^(C00-)[0-9]{3,5}$");
        boolean match=idpattern.matcher(CustomerlDTxt.getText()).matches();
        if(match){
            CustomerlDTxt.setStyle("-fx-border-color: lightgreen");
            btnAdd.setDisable(false);
        }else{
            CustomerlDTxt.setStyle("-fx-border-color: red");
            btnAdd.setDisable(true);

        }
    }

    public void PrintOnAction(ActionEvent actionEvent)  {


        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/Resouces/View/Reports/Edward.jrxml"));
            Connection connection= DBConnection.getInstance().getConnection();
            //2STEP----compile the reports
            JasperReport compileReport= JasperCompileManager.compileReport(load);

            //3STEP fill the informationn which reprt needed
            JasperPrint jasperPrint=  JasperFillManager.fillReport(compileReport,null,connection);
            //4STEP----

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}


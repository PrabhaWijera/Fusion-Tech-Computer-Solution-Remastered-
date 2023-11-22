package lk.ijse.Fusion.controller.Employee;

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
import lk.ijse.Fusion.dao.custom.impl.EmployeeDAOimpl;
import lk.ijse.Fusion.dto.EmployeeDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.EmployeeService;
import lk.ijse.Fusion.service.custom.impl.EmployeeServiceimpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeController {
    @FXML
    public AnchorPane employeeContext;
    @FXML
    public AnchorPane showWindowContext;
    @FXML
    public TextField addressTxt;
    @FXML
    public TextField phonetxt;
    @FXML
    public TextField emailTxt;
    @FXML
    public TextField joindateTxt;
    @FXML
    public TextField dobTxt;
    @FXML
    public TextField Employee_nameTxt;
    @FXML
    public TextField employee_NicTxt;
    @FXML
    public TextField Employe_IdTxt;

    @FXML
    public TextField jobroleTxt;
    @FXML
    public TextField genderTxt;
    public TableView employefullTBL;
    public TableColumn ColemployeeId;
    public TableColumn ColemployeeNic;
    public TableColumn ColName;
    public TableColumn ColAddress;
    public TableColumn ColphoneNumber;
    public TableColumn Colemail;
    public TableColumn Colgender;
    public TableColumn ColDob;
    public TableColumn ColJobrole;
    public TableColumn ColjoinDate;
    public Button addBtn;
    public AnchorPane pane;
   private  String EmployeeId ;
   private String  EmployeeNIC;
   private String Name ;
   private String Address;
   private String  Email ;
   private String Mobile;
   private String JobROLE ;
   private String Gender;
    private  String DOB  ;
    private String JoinDate;
    public EmployeeService employeeService;



    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
       EmployeeId = Employe_IdTxt.getText();
        try {
            EmployeeDTO employeedto = EmployeeDAOimpl.search(EmployeeId);
            if (employeedto != null) {
                fillData(employeedto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    public void searchOnAction(ActionEvent actionEvent) {
       EmployeeId  = Employe_IdTxt.getText();
        try {
              EmployeeDTO employeeDTO=employeeService.findAllEmployeesByEmployeeId(EmployeeId);
            if (employeeDTO != null) {
                fillData(employeeDTO);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillData(EmployeeDTO employeedto) {
        Employe_IdTxt.setText(employeedto.getEmployeeId());
        employee_NicTxt.setText(employeedto.getEmployeeNIC());
        Employee_nameTxt.setText(employeedto.getName());
        addressTxt.setText(employeedto.getAddress());
        emailTxt.setText(employeedto.getEmail());
        phonetxt.setText(employeedto.getMobile());
        jobroleTxt.setText(employeedto.getJobROLE());
        genderTxt.setText(employeedto.getGender());
        dobTxt.setText(employeedto.getDOB());
        joindateTxt.setText(employeedto.getJoinDate());
    }



    public void DeleteOnAction(ActionEvent actionEvent) {
       EmployeeId = Employe_IdTxt.getText();
          EmployeeNIC = employee_NicTxt.getText();
         Name = Employee_nameTxt.getText();
         Address = addressTxt.getText();
          Email = emailTxt.getText();
         Mobile = phonetxt.getText();
         JobROLE =  jobroleTxt.getText();
         Gender=genderTxt.getText();
         DOB = dobTxt.getText();
         JoinDate = joindateTxt.getText();

        EmployeeDTO employeedto = new EmployeeDTO(EmployeeId,EmployeeNIC,Name,Address,Email,Mobile,JobROLE,Gender,DOB,JoinDate );
        try {
            employeeService.deleteEmployee(EmployeeId);
            if (employeedto != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Employe_IdTxt.clear();employee_NicTxt.clear();Employee_nameTxt.clear();addressTxt.clear();emailTxt.clear();phonetxt.clear();jobroleTxt.clear();genderTxt.clear();dobTxt.clear();joindateTxt.clear();


    }

   public  void UpdateOnAction(ActionEvent actionEvent) {
       String EmployeeId = Employe_IdTxt.getText();
        String  EmployeeNIC = employee_NicTxt.getText();
        String Name = Employee_nameTxt.getText();
        String Address = addressTxt.getText();
        String  Email = emailTxt.getText();
        String Mobile = phonetxt.getText();
        String JobROLE =  jobroleTxt.getText();
        String Gender=genderTxt.getText();
        String DOB = dobTxt.getText();
        String JoinDate = joindateTxt.getText();

        EmployeeDTO employeedto = new EmployeeDTO(EmployeeId,EmployeeNIC,Name,Address,Email,Mobile,JobROLE,Gender,DOB,JoinDate );
        try {
            employeeService.updateAttends(employeedto);
            if (employeedto != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO Update!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
       Employe_IdTxt.clear();employee_NicTxt.clear();Employee_nameTxt.clear();addressTxt.clear();emailTxt.clear();phonetxt.clear();jobroleTxt.clear();genderTxt.clear();dobTxt.clear();joindateTxt.clear();


   }

@FXML
   void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String EmployeeId = Employe_IdTxt.getText();
        String  EmployeeNIC = employee_NicTxt.getText();
        String Name = Employee_nameTxt.getText();
        String Address = addressTxt.getText();
        String  Email = emailTxt.getText();
        String Mobile = phonetxt.getText();
        String JobROLE =  jobroleTxt.getText();
        String Gender=genderTxt.getText();
        String DOB = dobTxt.getText();
        String JoinDate = joindateTxt.getText();

        EmployeeDTO employeedto = new EmployeeDTO(EmployeeId,EmployeeNIC,Name,Address,Email,Mobile,JobROLE,Gender,DOB,JoinDate);
            EmployeeServiceimpl cd=ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    try{
        employeeService.saveEmployee(employeedto);
        if (employeedto != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employe Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
    }
        }catch (SQLException |ClassNotFoundException e){
        throw new RuntimeException(e);
    }
    Employe_IdTxt.clear();employee_NicTxt.clear();Employee_nameTxt.clear();addressTxt.clear();emailTxt.clear();phonetxt.clear();jobroleTxt.clear();genderTxt.clear();dobTxt.clear();joindateTxt.clear();

    }

    public void initialize() throws SQLException, ClassNotFoundException {
        employeeService= ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
        ColemployeeId.setCellValueFactory(new PropertyValueFactory("EmployeeId"));
        ColemployeeNic.setCellValueFactory(new PropertyValueFactory("EmployeeNIC"));
        ColName.setCellValueFactory(new PropertyValueFactory("Name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        Colemail.setCellValueFactory(new PropertyValueFactory("Email"));
        ColphoneNumber.setCellValueFactory(new PropertyValueFactory("Mobile"));
        ColJobrole.setCellValueFactory(new PropertyValueFactory("JobROLE"));
        ColDob.setCellValueFactory(new PropertyValueFactory("DOB"));
        ColjoinDate.setCellValueFactory(new PropertyValueFactory("JoinDate"));
        Colgender.setCellValueFactory(new PropertyValueFactory("Gender"));

        try {
            loadAllEmployee();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }



    private void loadAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("SELECT * FROM  fusiontech.Employee");
        ObservableList<EmployeeDTO> observableList = FXCollections.observableArrayList();
        while(resultSet.next()){
            observableList.add(
                    new EmployeeDTO(
                            resultSet.getString("EmployeeId"),
                            resultSet.getString("EmployeeNIC"),
                            resultSet.getString("Name"),
                            resultSet.getString("Address"),
                            resultSet.getString("Email"),
                            resultSet.getString("Mobile"),
                            resultSet.getString("JobROLE"),
                            resultSet.getString("DOB"),
                            resultSet.getString("JoinDate"),
                            resultSet.getString("Gender")
                    )
            );
        }
        employefullTBL.setItems(observableList);
    }

    public void AttendsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ATTENDS,pane);
    }


    public void EmpkeyOnRelalease(KeyEvent keyEvent) {
    }

    public void EnicOnRelease(KeyEvent keyEvent) {
        Pattern nicPtn=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");

        boolean match=nicPtn.matcher(employee_NicTxt.getText()).matches();
        if(match){
            employee_NicTxt.setStyle("-fx-border-color: lightgreen");
            addBtn.setDisable(false);
        }else{
            employee_NicTxt.setStyle("-fx-border-color: red");
            addBtn.setDisable(true);
        }
    }

    public void EnameKeyOnRelese(KeyEvent keyEvent) {
        Pattern nameptn=Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        boolean match=nameptn.matcher(Employee_nameTxt.getText()).matches();
        if(match){
            Employee_nameTxt.setStyle("-fx-border-color: lightgreen");
            addBtn.setDisable(false);
        }else{
            Employee_nameTxt.setStyle("-fx-border-color: red");
            addBtn.setDisable(true);
        }
    }

    public void DobKeyOnRelase(KeyEvent keyEvent) {
    }

    public void DateKeyOnRelese(KeyEvent keyEvent) {
    }

    public void EmailKeyOnRelese(KeyEvent keyEvent) {
        Pattern emailptn=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");

        boolean match=emailptn.matcher(emailTxt.getText()).matches();
        if(match){
            emailTxt.setStyle("-fx-border-color: lightgreen");
            addBtn.setDisable(false);
        }else{
            emailTxt.setStyle("-fx-border-color: red");
            addBtn.setDisable(true);
        }
    }

    public void PhoneKeyOnrelese(KeyEvent keyEvent) {

    }

    public void addressKeyOnRelese(KeyEvent keyEvent) {
        Pattern addressptn=Pattern.compile("^[^,\\\\]+[,\\\\\\s]+(.+?)\\s*(\\d{5})?$");

        boolean match=addressptn.matcher(addressTxt.getText()).matches();
        if(match){
            addressTxt.setStyle("-fx-border-color: lightgreen");
            addBtn.setDisable(false);
        }else{
            addressTxt.setStyle("-fx-border-color: red");
            addBtn.setDisable(true);
        }
    }

    public void JobRoleskeyOnRelaese(KeyEvent keyEvent) {

    }

    public void GenderKeyOnAction(KeyEvent keyEvent) {

    }

    public void Go_Salary(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALARY,pane);

    }


}

package lk.ijse.Fusion.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Employeedao;
import lk.ijse.Fusion.dao.custom.impl.EmployeeDAOimpl;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.EmployeeDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.EmployeeService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeServiceimpl implements EmployeeService {
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
    private final Connection connection;
    private final Convertor convertor;
    private   Employeedao employeeDAO;

    public EmployeeServiceimpl()  {
    connection= DBConnection.getInstance().getConnection();
    employeeDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE);
    convertor=new Convertor();


    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String EmployeeId = Employe_IdTxt.getText();
        try {
            EmployeeDTO employeedto = EmployeeDAOimpl.search(EmployeeId);
            if (employeedto != null) {
                fillData(employeedto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





  /*  public void searchOnAction(ActionEvent actionEvent) {
        String EmployeeId  = Employe_IdTxt.getText();
        try {
            EmployeeDTO employeedto = EmployeeDAOimpl.search(EmployeeId);
            if (employeedto != null) {
                fillData(employeedto);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
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




    public void initialize() {
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


    public void ReloadOnAction(ActionEvent actionEvent) {
        initialize();
    }



    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (employeeDAO.existByPk(employeeDTO.getEmployeeId())) throw new DuplicateException("EmployeeDTO already saved");
        employeeDAO.save(convertor.toEmployee(employeeDTO));

        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateAttends(EmployeeDTO employeeDTO) throws NotFoundException, SQLException, ClassNotFoundException {
        if(!employeeDAO.existByPk(employeeDTO.getEmployeeId())) throw new NotFoundException("Employeee not found");
        employeeDAO.update(convertor.toEmployee(employeeDTO));
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String EmployeeId) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {

        if (employeeDAO.existByPk(EmployeeId)) throw new NotFoundException("EmployeeDTO not found!!");
        try{
            employeeDAO.deleteByPk(EmployeeId);
        }catch (ConstraintViolationException e){
            throw new InUseException("EmployeeDTO Already in");
        }
    }

    @Override
    public EmployeeDTO findAllEmployeesByEmployeeId(String EmployeeId) throws SQLException, ClassNotFoundException {

        return convertor.fromEmployee(employeeDAO.findByPk(EmployeeId));
    }
}

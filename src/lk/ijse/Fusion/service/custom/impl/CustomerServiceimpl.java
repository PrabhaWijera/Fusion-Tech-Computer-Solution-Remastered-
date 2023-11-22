package lk.ijse.Fusion.service.custom.impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Customerdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.service.custom.CustomerService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceimpl implements CustomerService {


    @FXML
    public TextField CustomerlDTxt;

    public AnchorPane pane;
    @FXML
    public TextField addressTxt;
    @FXML
    public TableView<CustomerDTO> tblCus;
    private  Customerdao customerDAO;
    private final Connection connection;
    private final Convertor converor;


    public CustomerServiceimpl()  {
        connection=DBConnection.getInstance().getConnection();
        customerDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER);
        converor=new Convertor();
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


    public void PrintOnAction(ActionEvent actionEvent)  {
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/Fusion/View/Reports/Edward.jrxml"));
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

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (customerDAO.existByPk(customerDTO.getCustomerID()))throw new DuplicateException("CustomerDTO Already in!!");
        customerDAO.save(converor.toCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public boolean UpdateCustomer(CustomerDTO customerDTO) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if (customerDAO.existByPk(customerDTO.getCustomerID())) throw new NotFoundException("CustomerDTO not Found!!!");
       return customerDAO.update(converor.toCustomer(customerDTO));
    }

    @Override
    public boolean DeleteCustomer(String CustomerID) throws NotFoundException, InUseException, SQLException {
    if (customerDAO.existByPk(CustomerID))throw new NotFoundException("CustomerDTO not found!!!");
    try{
        customerDAO.deleteByPk(CustomerID);
    }catch (ConstraintViolationException | ClassNotFoundException e){
        throw new InUseException("CustomerDTO Already in here!!");
     }
        return false;
    }

    @Override
    public CustomerDTO findByCustomerId(String CustomerID) throws NotFoundException, SQLException, ClassNotFoundException {
        System.out.println("\n\n\nin customer service impl\n\n\n");
        return converor.fromCustomer(customerDAO.findByPk(CustomerID));
    }

}


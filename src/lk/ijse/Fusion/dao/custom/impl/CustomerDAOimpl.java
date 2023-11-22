package lk.ijse.Fusion.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Customerdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.entity.Customer;
import lk.ijse.Fusion.service.custom.CustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOimpl implements Customerdao {


    public static CustomerService customerService;
    private Connection connection;

    public CustomerDAOimpl(Connection connection) {
        this.connection = connection;
    }


    public static ArrayList<CustomerDTO> getCustomerList() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("select * from Customer");

        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();

        while (resultSet.next()) {
            customerDTOArrayList.add(new CustomerDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        return customerDTOArrayList;
    }

    public static CustomerDTO getCustomer(String CustomerID) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("select * from Customer where CustomerID = ?", CustomerID);

        if (resultSet.next()) {
            return new CustomerDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        }
        return null;
    }

    public static boolean addCustomer() throws ClassNotFoundException, SQLException {
        return Boolean.parseBoolean(null);

    }

    public static CustomerDTO search(String CustomerID) throws SQLException, ClassNotFoundException {
        return null;
    }


    public static ArrayList loadCustomerId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT CustomerID FROM customer";
        ResultSet result = CRUDutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    public static boolean remove(String CustomerID) throws SQLException, ClassNotFoundException {
        //  customerService.DeleteCustomer(CustomerID);
        //DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where CustomerID='" + CustomerID + "'") > 0;
        return false;
    }

/*
    public boolean update(CustomerDTO customerdto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC=? where CustomerID=?");
        System.out.println("hi ");
        stm.setObject(1, customerdto.getEmail());
        stm.setObject(2, customerdto.getPhoneNumber());
        stm.setObject(3, customerdto.getAddress());
        stm.setObject(4, customerdto.getCustomerName());
        stm.setObject(5, customerdto.getCustomerNIC());
        stm.setObject(6, customerdto.getCustomerID());

        //   customerService.UpdateCustomer(customerdto);
        return stm.executeUpdate() > 0;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC where CustomerID=?") > 0;

    }

*/
    @Override
    public Customer save(Customer customerdto) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?)";
        boolean b = DBUtil.executeUpdate(sql, customerdto.getCustomerID(), customerdto.getCustomerNIC(), customerdto.getCustomerName(), customerdto.getAddress(), customerdto.getPhoneNumber(), customerdto.getEmail());
        return b ? new Customer() : null;

    }

    @Override
    public boolean update(Customer customerdto) throws ClassNotFoundException, SQLException {
    /*boolean b= true;
    try {
        b=DBUtil.executeUpdate("UPDATE customer SET  Email=?, PhoneNumber=?, Address=?, CustomerName=?,CustomerNIC=? WHERE CustomerID= ? ",customerdto.getCustomerID());
    }catch (SQLException | ClassNotFoundException exception){

    }
    return b;*/
        return DBUtil.executeUpdate("UPDATE customer SET  Email=?, PhoneNumber=?, Address=?, CustomerName=?,CustomerNIC=? WHERE CustomerID= ? ",customerdto.getEmail(),customerdto.getPhoneNumber(),customerdto.getAddress(),customerdto.getCustomerName(),customerdto.getCustomerNIC(),customerdto.getCustomerID());
    }

    @Override
    public boolean deleteByPk(String CustomerID) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where CustomerID='" + CustomerID + "'") > 0;

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findByPk(String CustomerID) throws SQLException, ClassNotFoundException{

        String sql = "SELECT  * FROM fusiontech.customer WHERE CustomerID= ?";
        //customerService.findByCustomerId(CustomerID);
        ResultSet resultSet = CRUDutil.execute(sql, CustomerID);

        if (resultSet.next()) {
            System.out.println("\n\n\nCustomer not null\n\n\n");

            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }


    @Override
    public Customer save(CustomerDTO customerdto) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return null;
    }


}



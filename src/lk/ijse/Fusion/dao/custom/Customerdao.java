package lk.ijse.Fusion.dao.custom;

import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.entity.Customer;
import lk.ijse.Fusion.dao.CrudDAO;

import java.sql.SQLException;

public interface Customerdao extends CrudDAO<Customer,String> {
    Customer save(CustomerDTO customerdto) throws ConstraintViolationException, SQLException, ClassNotFoundException;
     //boolean update(CustomerDTO customerdto) throws SQLException, ClassNotFoundException;
}

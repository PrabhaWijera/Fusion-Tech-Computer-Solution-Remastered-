package lk.ijse.Fusion.service.custom;


import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface CustomerService extends SuperService {

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public boolean UpdateCustomer(CustomerDTO customerDTO) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public boolean DeleteCustomer(String CustomerID)throws  NotFoundException,InUseException, SQLException;

    public CustomerDTO findByCustomerId(String CustomerID) throws NotFoundException, SQLException, ClassNotFoundException;


}

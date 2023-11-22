package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.SuppliersDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface SuppliersService extends SuperService {

    public SuppliersDTO saveSuppliers(SuppliersDTO suppliersDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public SuppliersDTO UpdateSuppliers(SuppliersDTO suppliersDTO)throws NotFoundException, InUseException, SQLException, ClassNotFoundException;

    public boolean DeleteSuppliers(String SupplierID) throws NotFoundException,InUseException,SQLException;

   public SuppliersDTO findBySupplierId(String SupplierID) throws NotFoundException, SQLException, ClassNotFoundException;


}

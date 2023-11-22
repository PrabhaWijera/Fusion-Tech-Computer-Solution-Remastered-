package lk.ijse.Fusion.service.custom.impl;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;
import lk.ijse.Fusion.dao.custom.Supplierdao;

import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.SuppliersDTO;
import lk.ijse.Fusion.service.custom.SuppliersService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;


public class SuppliersServiceimpl implements SuppliersService {
    private Convertor convertor;
    private Connection connection;
    private Supplierdao suppliersDAO;

    public void SuppliersService() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        suppliersDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.SUPPLIERS);
        convertor=new Convertor();
      //  suppliersDAO= ServiceFactory.getInstance().getService(ServiceType.ATTENDS);

    }




    @Override
    public SuppliersDTO saveSuppliers(SuppliersDTO suppliersDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (suppliersDAO.existByPk(suppliersDTO.getSupplierID())) throw new DuplicateException("SuppliersDTO Already Saved!!!!");
        suppliersDAO.save(convertor.toSuppliers(suppliersDTO));
        return suppliersDTO;
    }

    @Override
    public SuppliersDTO UpdateSuppliers(SuppliersDTO suppliersDTO) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        if(!suppliersDAO.existByPk(suppliersDTO.getSupplierID())) throw new NotFoundException("SuppliersDTO  not here !!");
        suppliersDAO.update(convertor.toSuppliers(suppliersDTO));
        return suppliersDTO;
    }

    @Override
    public boolean DeleteSuppliers(String SupplierID) throws NotFoundException, InUseException, SQLException {
    if (!suppliersDAO.existByPk(SupplierID)) throw new NotFoundException("SuppliersDTO not found!!");
    try{
        suppliersDAO.deleteByPk(SupplierID);
    }catch (ConstraintViolationException | ClassNotFoundException e){
        throw new InUseException("SuppliersDTO Already In!!!!");
    }
        return false;
    }

    @Override
    public SuppliersDTO findBySupplierId(String SupplierID) throws NotFoundException, SQLException, ClassNotFoundException {

        if (! suppliersDAO.existByPk(SupplierID)) throw new NotFoundException("SuppliersDTO not found!!!!");

        return convertor.fromSuppliers(suppliersDAO.findByPk(SupplierID));
    }



}

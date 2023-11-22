package lk.ijse.Fusion.service.custom.impl;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Salarydao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.SalaryDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.SalaryService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;

public class SalaryFormServiceimpl implements SalaryService {
    public TextField amountTxt;
    public TextField employeeIdTxt;
    public TextField SalaryIdtxt;
    public TextField salaryMethodTxt;
    public TextField txtDetail;
    public AnchorPane pane;

    private final Convertor convertor;
    private final Connection connection;
    private   Salarydao salaryDAO;



    public SalaryFormServiceimpl()  {
        connection= DBConnection.getInstance().getConnection();
        salaryDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.SALARY);
        convertor=new Convertor();
        salaryDAO= ServiceFactory.getInstance().getService(ServiceType.SALARY);

    }





    @Override
    public SalaryDTO saveSalary(SalaryDTO salaryDTO) throws DuplicateException, SQLException, ClassNotFoundException {
      if (!salaryDAO.existByPk(salaryDTO.getSalaryID()))throw new DuplicateException("SalaryDTO already add!!");
        salaryDAO.save(convertor.toSalary(salaryDTO));
        return salaryDTO;
    }

    @Override
    public SalaryDTO updateSalary(SalaryDTO salaryDTO) throws NotFoundException, SQLException, ClassNotFoundException {
       if (!salaryDAO.existByPk(String.valueOf(Integer.valueOf(salaryDTO.getSalaryID()))))throw new NotFoundException("SalaryDTO not updated!!!");
        salaryDAO.update(convertor.toSalary(salaryDTO));
        return salaryDTO;
    }

    @Override
    public boolean DeleteSalary(String SalaryID) throws NotFoundException, InUseException {
    if (salaryDAO.existByPk(SalaryID))throw new NotFoundException("SalaryDTO not Deleted!!");
    try{
        salaryDAO.deleteByPk(SalaryID);
    }catch(ConstraintViolationException e){
        throw new InUseException("SalaryDTO Already deleted!!!");
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
        return false;
    }
}

package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.SalaryDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface SalaryService extends SuperService {


    public SalaryDTO saveSalary(SalaryDTO salaryDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public SalaryDTO updateSalary(SalaryDTO salaryDTO) throws NotFoundException, SQLException, ClassNotFoundException;

    public boolean DeleteSalary(String SalaryID) throws NotFoundException, InUseException;

}

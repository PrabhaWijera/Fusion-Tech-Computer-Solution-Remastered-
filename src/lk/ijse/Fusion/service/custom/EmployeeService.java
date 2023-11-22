package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.EmployeeDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface EmployeeService extends SuperService {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException, SQLException, ClassNotFoundException;
    public EmployeeDTO updateAttends(EmployeeDTO employeeDTO) throws NotFoundException, SQLException, ClassNotFoundException;

    public void deleteEmployee(String AttendID) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public EmployeeDTO findAllEmployeesByEmployeeId(String AttendID) throws SQLException, ClassNotFoundException;





}


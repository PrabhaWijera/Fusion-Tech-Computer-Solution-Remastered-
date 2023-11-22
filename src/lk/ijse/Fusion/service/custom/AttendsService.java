package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.AttendenceDTO;

import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface AttendsService extends SuperService {
    public AttendenceDTO saveAttends(AttendenceDTO attendsDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public AttendenceDTO updateAttends(AttendenceDTO attendsDTO) throws NotFoundException, SQLException, ClassNotFoundException;
    public AttendenceDTO SearchAttends(String AttendID) throws NotFoundException, SQLException, ClassNotFoundException;

    public void DeleteAttends(String AttendID) throws NotFoundException, InUseException;

    public List<AttendenceDTO>loadAllAttends() throws SQLException, ClassNotFoundException;



}

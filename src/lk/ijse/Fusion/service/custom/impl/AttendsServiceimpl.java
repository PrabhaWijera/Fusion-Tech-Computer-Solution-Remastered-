package lk.ijse.Fusion.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Attendsdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;

import lk.ijse.Fusion.dto.AttendenceDTO;
import lk.ijse.Fusion.dao.custom.impl.AttendsDAOimpl;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.AttendsService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AttendsServiceimpl implements AttendsService {
    private final Convertor convertor;
    private final Connection connection;
    private Attendsdao attendsDAO;



    public AttendsServiceimpl()  {
         connection= DBConnection.getInstance().getConnection();
        attendsDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ATTENDS);
        convertor=new Convertor();

       // attendsDAO= ServiceFactory.getInstance().getService(ServiceType.ATTENDS);

    }



    @Override
    public AttendenceDTO saveAttends(AttendenceDTO attendsDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (attendsDAO.existByPk(attendsDTO.getAttendID()))throw new DuplicateException("Attends already Saved!!!");
        attendsDAO.save(convertor.toAttends(attendsDTO));
        return attendsDTO;
    }

    @Override
    public AttendenceDTO updateAttends(AttendenceDTO attendsDTO) throws NotFoundException, SQLException, ClassNotFoundException {
        if (!attendsDAO.existByPk(attendsDTO.getAttendID())) throw new NotFoundException("Attends not Found");
        attendsDAO.update(convertor.toAttends(attendsDTO));
        return attendsDTO;
    }

    @Override
    public AttendenceDTO SearchAttends(String AttendID) throws NotFoundException, SQLException, ClassNotFoundException {

        if (!attendsDAO.existByPk(AttendID)) throw new NotFoundException("Attends not");
        return convertor.fromAttends(attendsDAO.findByPk(AttendID));
    }

    @Override
    public void DeleteAttends(String AttendID) throws NotFoundException, InUseException {
        if (attendsDAO.existByPk(AttendID)) throw new NotFoundException("Attend Not Found!!!");
        try{
            attendsDAO.deleteByPk(AttendID);
        }catch (ConstraintViolationException e){
            throw new InUseException("Attends Already in !!");
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    @Override
    public List<AttendenceDTO> loadAllAttends() throws SQLException, ClassNotFoundException {
        return null;
    }


}

package lk.ijse.Fusion.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Attendsdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.AttendenceDTO;
import lk.ijse.Fusion.entity.Attendence;
import lk.ijse.Fusion.service.custom.AttendsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AttendsDAOimpl implements Attendsdao {
    private final Connection connection;
    public AttendsService attendsService;
    private ChoiceBox<Attendence> TblfullAttends;

    public AttendsDAOimpl(Connection connection) {
        this.connection = connection;

    }


    public static boolean addAttends(AttendenceDTO attendencedto) throws SQLException, ClassNotFoundException {
        return Boolean.parseBoolean(null);
    }

    public static AttendenceDTO search(String attendID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From fusiontech.attendence   WHERE  AttendID = ? ";
        ResultSet resultSet = CRUDutil.execute(sql, attendID);

        if (resultSet.next()) {
            return new AttendenceDTO(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
        }
        return null;

    }

    public static boolean update(AttendenceDTO attendencedto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE fusiontech.attendencedto set   OutTime=?, InTime=?, AttendDate=? WHERE AttendID=? ");
        //"Update attendencedto set stetus=?,OutTime=?, InTime=?, AttendDate=?   where AttendID=?"

        stm.setObject(1, attendencedto.getOutTime());
        stm.setObject(2, attendencedto.getInTime());
        stm.setObject(3, attendencedto.getAttendDate());
        stm.setObject(4, attendencedto.getAttendID());
        return stm.executeUpdate() > 0;
    }

    public static boolean remove(String AttendID) throws SQLException, ClassNotFoundException {

        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From attendence where AttendID='" + AttendID + "'") > 0;

    }

    @Override
    public Attendence save(Attendence attendence) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO fusiontech.attendence VALUES (?, ?, ?, ?)";
        boolean b=DBUtil.executeUpdate(sql, attendence.getAttendID(), attendence.getAttendDate(), attendence.getInTime(), attendence.getOutTime());

            return b ? new Attendence() : null;

    }

    @Override
    public boolean update(Attendence entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String AttendID) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From attendence where AttendID='" + AttendID + "'") > 0;

    }

    @Override
    public List<Attendence> findAll() {
        return null;
    }

    @Override
    public Attendence findByPk(String pk) {
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

    public void loadAllAttends() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("SELECT * FROM fusiontech.attendence");
        ObservableList<Attendence> observableList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            observableList.add(
                    new lk.ijse.Fusion.entity.Attendence(
                            resultSet.getString("AttendID"),
                            resultSet.getInt("AttendDate"),
                            resultSet.getString("InTime"),
                            resultSet.getString("OutTime")

                    )
            );
        }
        TblfullAttends.setItems(observableList);
    }
}

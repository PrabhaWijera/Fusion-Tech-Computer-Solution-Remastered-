package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Salarydao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.SalaryDTO;
import lk.ijse.Fusion.entity.Salary;
import lk.ijse.Fusion.service.custom.SalaryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SalaryDAOimpl implements Salarydao {
    private final Connection connection;
    public static SalaryService salaryService;
    public SalaryDAOimpl(Connection connection) {
        this.connection = connection;
    }
    public void initialize() throws SQLException, ClassNotFoundException {
      //  salaryService= ServiceFactory.getInstance().getService(ServiceType.SALARY);
    }

    public static boolean addsala(SalaryDTO salarydto) throws SQLException, ClassNotFoundException {
      //  salaryService.saveSalary(salarydto);
        return Boolean.parseBoolean(null);

    }

    public static boolean remove(String SalaryID) throws SQLException, ClassNotFoundException {
    //    salaryService.DeleteSalary(SalaryID);
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Salary where SalaryID='" + SalaryID + "'") > 0;
    return false;
    }

    public static boolean update(SalaryDTO salarydto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update SalaryDTO set Details=?,SalaryMethod=?, SalaryAmount=?, EmployeeID=?   where SalaryID=?");
      //  salaryService.updateSalary(salarydto);
        stm.setObject(1, salarydto.getDetails());
        stm.setObject(2, salarydto.getSalaryMethod());
        stm.setObject(3, salarydto.getSalaryAmount());
        stm.setObject(4, salarydto.getEmployeeID());
        stm.setObject(5, salarydto.getSalaryID());
        return  stm.executeUpdate()>0;
    }

    @Override
    public Salary save(Salary salary) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Salary VALUES (?, ?, ?, ?, ?)";
       boolean b= DBUtil.executeUpdate(sql, salary.getSalaryID(), salary.getEmployeeID(), salary.getSalaryAmount(), salary.getSalaryMethod(), salary.getDetails());
    return b ? new Salary() : null;
    }

    @Override
    public boolean update(Salary entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String SalaryID) throws ConstraintViolationException, SQLException, ClassNotFoundException {

        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Salary where SalaryID='" + SalaryID + "'") > 0;

    }

    @Override
    public List<Salary> findAll() {
        return null;
    }

    @Override
    public Salary findByPk(String SalaryID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM fusiontech.salary WHERE SalaryID= ?";
        //customerService.findByCustomerId(CustomerID);
        ResultSet resultSet = CRUDutil.execute(sql, SalaryID);


        if (resultSet.next()) {
            System.out.println("\n\n\nCustomer not null\n\n\n");

            return new Salary(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }
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
}

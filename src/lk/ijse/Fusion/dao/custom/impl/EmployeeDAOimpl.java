package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Employeedao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.EmployeeDTO;
import lk.ijse.Fusion.entity.Employee;
import lk.ijse.Fusion.service.custom.EmployeeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOimpl implements Employeedao {
    private static EmployeeService employeeService;
    private final Connection connection;

    public EmployeeDAOimpl(Connection connection) {

        this.connection = connection;
    }
    public void initialize() throws SQLException, ClassNotFoundException {
  //  employeeService= ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    }


    public static boolean addEmployee(EmployeeDTO employeedto) throws SQLException, ClassNotFoundException {
     //  employeeService.saveEmployee(employeedto);
        return Boolean.parseBoolean(null);
    }

    public static EmployeeDTO search(String employeeId) throws SQLException, ClassNotFoundException {
   return null;
    }


    public static boolean update(EmployeeDTO employeedto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update EmployeeDTO set Gender=?, JoinDate=?, DOB=? ,JobROLE=?, Mobile=? ,Email=?,Address=?, Name=?, EmployeeNIC=? where EmployeeId=?");
        stm.setObject(1, employeedto.getGender());
        stm.setObject(2, employeedto.getJobROLE() );
        stm.setObject(3,  employeedto.getDOB());
        stm.setObject(4, employeedto.getJobROLE());
        stm.setObject(5, employeedto.getMobile() );
        stm.setObject(6, employeedto.getEmail());
        stm.setObject(7, employeedto.getAddress());
        stm.setObject(8, employeedto.getName());
        stm.setObject(9, employeedto.getEmployeeNIC());
        stm.setObject(10, employeedto.getEmployeeId());

     //   employeeService.updateAttends(employeedto);
        return  stm.executeUpdate()>0;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC where CustomerID=?") > 0;

    }


    public static boolean remove(String EmployeeId) throws SQLException, ClassNotFoundException {
      // employeeService.deleteEmployee(EmployeeId);
return false;
    }

    @Override
    public Employee save(Employee employee) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO fusiontech.employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       boolean b =DBUtil.executeUpdate(sql, employee.getEmployeeId(), employee.getEmployeeNIC(), employee.getName(), employee.getAddress(), employee.getEmail(), employee.getMobile(), employee.getJobROLE(), employee.getDOB(), employee.getJoinDate(), employee.getGender());
        //boolean b = DBUtil.executeUpdate(sql, customerdto.getCustomerID(), customerdto.getCustomerNIC(), customerdto.getCustomerName(), customerdto.getAddress(), customerdto.getPhoneNumber(), customerdto.getEmail());
        return b ? new Employee() : null;

    }

    @Override
    public boolean update(Employee entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String EmployeeId) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Employee where EmployeeId='" + EmployeeId + "'") > 0;

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByPk(String employeeId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM fusiontech.employee WHERE EmployeeId=?  ";
        ResultSet resultSet = CRUDutil.execute(sql, employeeId);
        //   employeeService.findAllEmployeesByEmployeeId(employeeId);
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)

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

package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Supplierdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.SuppliersDTO;
import lk.ijse.Fusion.entity.Suppliers;
import lk.ijse.Fusion.service.custom.SuppliersService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SuppliersDAOimpl implements Supplierdao {
    private final Connection connection;
    public static SuppliersService suppliersService;
    public SuppliersDAOimpl(Connection connection) {

        this.connection = connection;
    }

    public void initilize() throws SQLException, ClassNotFoundException {
     //   suppliersService= ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
    }
    public static boolean addsupplly(SuppliersDTO suppliersdto) throws SQLException, ClassNotFoundException {
 /*       String sql = "INSERT INTO  fusiontech.suppliers VALUES (?, ?, ?, ?, ?, ?, ?)";
 */     //  suppliersService.saveSuppliers(suppliersdto);
/*
        return CRUDutil.execute(sql, suppliersdto.getSupplierID(), suppliersdto.getSupplierName(), suppliersdto.getSupplierEmail(), suppliersdto.getSupplierContact(), suppliersdto.getPrice(), suppliersdto.getItemCode(), suppliersdto.getAmount());
*/
        return Boolean.parseBoolean(null);

    }

    public static boolean remove(String SupplierID) throws SQLException, ClassNotFoundException {
     //  suppliersService.DeleteSuppliers(SupplierID);
        return false;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From suppliers where SupplierID='" + SupplierID + "'") > 0;
    }

    public static boolean update(SuppliersDTO suppliersdto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update suppliersdto set Amount=?,ItemCode=?, Price=?, SupplierContact=? ,SupplierEmail=?, SupplierName=? where SupplierID=?");
      //  suppliersService.UpdateSuppliers(suppliersdto);
        stm.setObject(1, suppliersdto.getAmount());
        stm.setObject(2, suppliersdto.getItemCode());
        stm.setObject(3, suppliersdto.getPrice());
        stm.setObject(4, suppliersdto.getSupplierContact());
        stm.setObject(5, suppliersdto.getSupplierEmail());
        stm.setObject(6, suppliersdto.getSupplierName());
        stm.setObject(7, suppliersdto.getSupplierID());
        return  stm.executeUpdate()>0;

    }


    public static SuppliersDTO search(String supplierID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From fusiontech.suppliers WHERE SupplierID = ? ";
        ResultSet resultSet = CRUDutil.execute(sql, supplierID);
     //   suppliersService.findBySupplierId(supplierID);
        if (resultSet.next()) {
            return new SuppliersDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;


    }

    @Override
    public Suppliers save(Suppliers suppliers) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO  fusiontech.suppliers VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean b= DBUtil.executeUpdate(sql, suppliers.getSupplierID(), suppliers.getSupplierName(), suppliers.getSupplierEmail(), suppliers.getSupplierContact(), suppliers.getPrice(), suppliers.getItemCode(), suppliers.getAmount());
        return b ? new Suppliers() : null;
    }

    @Override
    public boolean update(Suppliers entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String SupplierID) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From suppliers where SupplierID='" + SupplierID + "'") > 0;
    }

    @Override
    public List<Suppliers> findAll() {
        return null;
    }

    @Override
    public Suppliers findByPk(String pk) {
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

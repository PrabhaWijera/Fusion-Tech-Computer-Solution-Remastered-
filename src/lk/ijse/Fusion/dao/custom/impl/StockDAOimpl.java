package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.dao.custom.Stockdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.StockDTO;
import lk.ijse.Fusion.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StockDAOimpl implements Stockdao {
    private final Connection connection;


    public StockDAOimpl(Connection connection) {

        this.connection = connection;
    }


    public static boolean addStock(StockDTO stockdto) throws SQLException, ClassNotFoundException {
        return Boolean.parseBoolean(null);

    }

    public static boolean remove(String StockID) throws SQLException, ClassNotFoundException {
      //  return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Stock where StockID='" + StockID + "'") > 0;
        return false;
    }

    public static boolean update(StockDTO stockdto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update StockDTO set stockQty =?,StockDate=?  where StockID=?");

        stm.setObject(3, stockdto.getStockID());
        stm.setObject(2, stockdto.getStockDate());
        stm.setObject(1, stockdto.getStockQty());


        return  stm.executeUpdate()>0;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC where CustomerID=?") > 0;

    }

    @Override
    public Stock save(Stock stock) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO fusiontech.stock VALUES (?, ?, ?)";
        boolean b= DBUtil.executeUpdate(sql, stock.getStockID(), stock.getStockDate(), stock.getStockQty());
    return b ? new Stock(): null;
    }

    @Override
    public boolean update(Stock entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String StockID) throws ConstraintViolationException, SQLException, ClassNotFoundException {

        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Stock where StockID='" + StockID + "'") > 0;
    }

    @Override
    public List<Stock> findAll() {
        return null;
    }

    @Override
    public Stock findByPk(String pk) {
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

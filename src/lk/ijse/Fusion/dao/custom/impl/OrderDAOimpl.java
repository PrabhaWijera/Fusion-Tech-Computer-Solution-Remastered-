package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Orderdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.dto.OrdersDTO;
import lk.ijse.Fusion.entity.Orders;
import lk.ijse.Fusion.service.custom.OrdersService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOimpl implements Orderdao {

    private static OrdersService ordersService;
    private Connection connection;

    public OrderDAOimpl(Connection connection) {
        this.connection = connection;
    }

    public OrderDAOimpl() {

    }

    public static String getOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM fusiontech.orders ORDER BY OrderId DESC LIMIT 1";
        ResultSet result = CRUDutil.execute(sql);
        if (result.next()) {
            return result.getString(1);
        }
        return null;
    }

    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String orderId = getOrderId();
        if (orderId != null) {
            String[] split = orderId.split("[D]");
            int OrderID = Integer.parseInt(split[1]);
            OrderID += 1;
            return String.format( "D%03d",OrderID) ;
        }
        return "D001";
    }

    public static void save(OrdersDTO ordersdto) {
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        //     ordersService= ServiceFactory.getInstance().getService(ServiceType.ORDER);
    }

    public static boolean saveOrder(OrdersDTO ordersdto) throws SQLException, ClassNotFoundException {
        return CRUDutil.execute("INSERT INTO orders VALUES (?,?,?)",
                ordersdto.getOrderID(), ordersdto.getOrderDate(), ordersdto.getCustomerID());

    }

    public boolean saveOrderDetails(ArrayList<OrderDetailDTO> details) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : details) {
            boolean isDetailSaved = CRUDutil.execute("INSERT INTO ordersdetail VALUES (?,?,?,?)",
                    od.getOrderId(),
                    od.getItemCode(),
                    od.getQty(),
                    od.getUnitPrice()
            );
            if (isDetailSaved) {
                if (!updateQty(od.getItemCode(), od.getQty())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, String qty) throws SQLException, ClassNotFoundException {
        return CRUDutil.execute("UPDATE item SET qtyOnHand=qtyOnHand-? WHERE ItemCode=?", qty, itemCode);
    }

    @Override
    public Orders save(Orders entity) throws ConstraintViolationException {
        return null;
    }

    @Override
    public boolean update(Orders entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String pk) throws ConstraintViolationException {

        return false;
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public Orders findByPk(String pk) {
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







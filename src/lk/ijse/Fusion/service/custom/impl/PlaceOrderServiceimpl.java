package lk.ijse.Fusion.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.dao.custom.impl.OrderDAOimpl;
import lk.ijse.Fusion.dao.custom.impl.OrderDetailsDaoimpl;
import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.dto.OrdersDTO;
import lk.ijse.Fusion.service.custom.PlaceOrderService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderServiceimpl implements PlaceOrderService {

    private OrderDAOimpl orderDAOimpl=new OrderDAOimpl();


    @Override
    public boolean getmangeOrder(ArrayList<OrderDetailDTO> details, OrdersDTO ordersdto) throws SQLException {
        Connection connection=null;
        try{
            connection=  DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSaved= OrderDAOimpl.saveOrder(ordersdto);
            if(isOrderSaved){
                boolean isDetailsSaved= OrderDetailsDaoimpl.saveOrderDetails(details);
                if(isDetailsSaved){
                    connection.commit();
                    return true;

                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch(SQLException | ClassNotFoundException e){
            throw new  RuntimeException(e);
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }


}

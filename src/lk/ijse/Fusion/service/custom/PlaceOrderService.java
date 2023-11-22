package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.dto.OrdersDTO;
import lk.ijse.Fusion.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderService extends SuperService {
    boolean getmangeOrder(ArrayList<OrderDetailDTO> details, OrdersDTO ordersdto) throws SQLException;
}

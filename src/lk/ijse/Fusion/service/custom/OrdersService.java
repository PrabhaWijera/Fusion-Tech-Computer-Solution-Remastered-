package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.OrdersDTO;
import lk.ijse.Fusion.service.SuperService;

public interface OrdersService extends SuperService {
    

    boolean save(OrdersDTO ordersdto);

    boolean saveOrder(OrdersDTO ordersdto);
}

package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface PlaceOrderFromService extends SuperService {
    public OrderDetailDTO saveorder(OrderDetailDTO orderDetailDTO)throws DuplicateException;


    public OrderDetailDTO updateOrder(OrderDetailDTO orderDetailDTO)throws NotFoundException;

    public void deleteOrder(String OrderId) throws NotFoundException, SQLException, InUseException,ClassNotFoundException;



}

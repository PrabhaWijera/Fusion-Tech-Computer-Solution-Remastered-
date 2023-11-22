package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailService extends SuperService {
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO)throws DuplicateException;


    public OrderDetailDTO updateOrderDetails(OrderDetailDTO orderDetailDTO) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;


    boolean saveOrderDetails(ArrayList<OrderDetailDTO> details);
}

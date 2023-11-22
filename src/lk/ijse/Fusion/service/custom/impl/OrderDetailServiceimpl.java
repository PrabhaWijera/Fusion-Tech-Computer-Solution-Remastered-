package lk.ijse.Fusion.service.custom.impl;

import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.service.custom.OrderDetailService;
import lk.ijse.Fusion.service.custom.OrdersService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailServiceimpl implements OrderDetailService {
    @Override
    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO) throws DuplicateException {
        return null;
    }

    @Override
    public OrderDetailDTO updateOrderDetails(OrderDetailDTO orderDetailDTO) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailDTO> details) {
        return false;
    }
}

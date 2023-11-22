package lk.ijse.Fusion.service.custom;

import lk.ijse.Fusion.dto.StockDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.SQLException;

public interface StockService extends SuperService {

    public StockDTO saveStock(StockDTO stockDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public  StockDTO updateStock(StockDTO stockDTO) throws NotFoundException, SQLException, ClassNotFoundException;

    public void deleteStock(String StockID) throws NotFoundException, InUseException,SQLException;
}

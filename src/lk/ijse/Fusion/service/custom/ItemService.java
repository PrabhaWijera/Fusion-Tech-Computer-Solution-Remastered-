package lk.ijse.Fusion.service.custom;

import javassist.NotFoundException;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.service.SuperService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;

import java.sql.SQLException;

public interface ItemService extends SuperService {
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException, SQLException, ClassNotFoundException;

    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException, SQLException, ClassNotFoundException;

    public boolean deleteItem(String ItemCode) throws NotFoundException, InUseException, SQLException,ClassNotFoundException;

    public ItemDTO findByItemId(String ItemCode) throws lk.ijse.Fusion.service.exception.NotFoundException, SQLException, ClassNotFoundException;


}

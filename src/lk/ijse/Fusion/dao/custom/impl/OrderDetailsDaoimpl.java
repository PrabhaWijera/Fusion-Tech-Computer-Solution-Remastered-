package lk.ijse.Fusion.dao.custom.impl;

import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.OrderDetails;
import lk.ijse.Fusion.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDaoimpl  implements OrderDetails {
    public static boolean saveOrderDetails(ArrayList<OrderDetailDTO> details) throws SQLException, ClassNotFoundException {
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
    private static boolean updateQty(String itemCode, String qty) throws SQLException, ClassNotFoundException {
        return CRUDutil.execute("UPDATE item SET qtyOnHand=qtyOnHand-? WHERE ItemCode=?", qty, itemCode);
    }
}

package lk.ijse.Fusion.dao.custom.impl;

import javafx.fxml.FXML;

import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.Itemdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dao.util.DBUtil;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.entity.Item;
import lk.ijse.Fusion.service.custom.ItemService;
import lk.ijse.Fusion.service.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOimpl implements Itemdao {
    private final Connection connection;
    private static ItemService itemService;

    public ItemDAOimpl(Connection connection) {

        this.connection = connection;
    }


    public static boolean addItem(ItemDTO itemdto) throws SQLException, ClassNotFoundException {
        return Boolean.parseBoolean(null);

    }


    public static ItemDTO search(String itemCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM fusiontech.item WHERE ItemCode= ? ";
        ResultSet resultSet = CRUDutil.execute(sql, itemCode);
      //  itemService.findAllItemsByItemCode(itemCode);
        if (resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    public static boolean remove(String ItemCode) throws SQLException, ClassNotFoundException {
        //     itemService.deleteItem(ItemCode);
        return  false;
    }

    public static boolean update(ItemDTO itemdto) throws SQLException, ClassNotFoundException, NotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update ItemDTO set ItemType=?, QtyOnHand=?, ItemPrice=? ,ItemDescription=?, ItemName=? where ItemCode=?");
    //   itemService.updateItem(itemdto);
        stm.setObject(6, itemdto.getItemCode());
        stm.setObject(5, itemdto.getItemName());
        stm.setObject(4, itemdto.getItemDescription());
        stm.setObject(3, itemdto.getItemPrice());
        stm.setObject(2, itemdto.getQtyOnHand());
        stm.setObject(1, itemdto.getItemType());

        return stm.executeUpdate() > 0;
        //return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Update CustomerDTO set Email=?, PhoneNumber=?, Address=? ,CustomerName=?, CustomerNIC where CustomerID=?") > 0;
    }

    @FXML
    public static ArrayList<String> loaditemIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT  ItemCode FROM Item";
        ResultSet result = CRUDutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }


    public static ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ItemCode FROM Item";
        ResultSet result = CRUDutil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }


    public static ArrayList<String> loadItemTypes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ItemType FROM Item";
        ResultSet result = CRUDutil.execute(sql);

        ArrayList<String> codeList = new ArrayList<>();

        while (result.next()) {
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    public static ItemDTO searchType(String ItemType) throws SQLException, ClassNotFoundException {
        return null;
    }




    public static boolean updateQty(String itemCode,int qty) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement("Update ItemDTO set QtyOnHand=? where ItemCode=?");
            stm.setObject(2, itemCode);
            stm.setObject(1, qty);

        return stm.executeUpdate() > 0;
    }


    public static boolean updateQty(ArrayList<OrderDetailDTO> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update ItemDTO set QtyOnHand=? where ItemCode=?");
        stm.setObject(2, orderDetailDTOS);
        stm.setObject(1, orderDetailDTOS);

        return stm.executeUpdate() > 0;
    }


    public static ArrayList loadItemId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT ItemCode FROM item";
        ResultSet result = CRUDutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

    @Override
    public Item save(Item item) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO fusiontech.item VALUES (?, ?, ?, ?, ?, ?)";
        // itemService.saveItem(itemdto);
        boolean b= DBUtil.executeUpdate(sql, item.getItemCode(), item.getItemName(), item.getItemDescription(), item.getItemPrice(), item.getQtyOnHand(), item.getItemType());
        return b ? new Item() : null;
    }

    @Override
    public boolean update(Item entity) throws ConstraintViolationException, SQLException {
        return Boolean.parseBoolean(null);
    }

    @Override
    public boolean deleteByPk(String ItemCode) throws ConstraintViolationException, SQLException, ClassNotFoundException {

        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Item where ItemCode='" + ItemCode + "'") > 0;

    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public Item findByPk(String ItemCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From fusiontech.Item WHERE ItemCode = ? ";
        ResultSet resultSet = CRUDutil.execute(sql, ItemCode);

        if (resultSet.next()) {
            System.out.println("items Not null");
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
        }
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
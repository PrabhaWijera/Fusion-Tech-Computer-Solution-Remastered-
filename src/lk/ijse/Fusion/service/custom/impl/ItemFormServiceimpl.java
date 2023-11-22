package lk.ijse.Fusion.service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javassist.NotFoundException;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Itemdao;
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.service.custom.ItemService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.util.Convertor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormServiceimpl implements ItemService {
    public AnchorPane ItemContext;
    public TextField item_ccodeTxt;
    public TextField item_nameTxt;
    public TextField item_priceTxt;
    public TextField descriptionTxt;
    public TextField QTYTxt;

    public TextField itemtypeTxt;
    public TableView <ItemDTO>tblItemAll;
    public TableColumn ColItemCode;
    public TableColumn ColItemType;
    public TableColumn ColItemName;
    public TableColumn ColItemPrice;

    public Button addbtn;
    private final Connection connection;
    private  final Convertor convertor;
    private  Itemdao itemDAO;


    public ItemFormServiceimpl()  {
        connection=DBConnection.getInstance().getConnection();
        itemDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ITEM);
        convertor = new Convertor();
     //  itemDAO= ServiceFactory.getInstance().getService(ServiceType.ITEM);

    }



    private void fillData(ItemDTO itemdto) {
        item_ccodeTxt.setText(itemdto.getItemCode());
        itemtypeTxt.setText(itemdto.getItemType());
        item_nameTxt.setText(itemdto.getItemName());
        item_priceTxt.setText(String.valueOf(itemdto.getItemPrice()));
        descriptionTxt.setText(itemdto.getItemDescription());
        QTYTxt.setText(String.valueOf(itemdto.getQtyOnHand()));

    }

    public void initialize() {
        ColItemCode .setCellValueFactory(new PropertyValueFactory("ItemCode"));
        ColItemType .setCellValueFactory(new PropertyValueFactory("ItemType"));
         ColItemName.setCellValueFactory(new PropertyValueFactory("ItemName"));
         ColItemPrice.setCellValueFactory(new PropertyValueFactory("ItemPrice"));

        try {
            loadAllItem();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    private void loadAllItem() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("SELECT * FROM  Item");
        ObservableList<ItemDTO> observableList = FXCollections.observableArrayList();
        while(resultSet.next()){
            observableList.add(
                    new ItemDTO(
                            resultSet.getString("ItemCode"),
                            resultSet.getString("ItemName"),
                            resultSet.getString("ItemDescription"),
                            resultSet.getDouble("ItemPrice"),
                            resultSet.getInt("QtyOnHand"),
                            resultSet.getString("ItemType")
                    )
            );
        }
        tblItemAll.setItems(observableList);
    }






    public void PrintOnAction(ActionEvent actionEvent) {

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/Resouces/View/Reports/Item.jrxml"));
            Connection connection= DBConnection.getInstance().getConnection();
            //2STEP----compile the reports
            JasperReport compileReport= JasperCompileManager.compileReport(load);

            //3STEP fill the informationn which reprt needed
            JasperPrint jasperPrint=  JasperFillManager.fillReport(compileReport,null,connection);
            //4STEP----

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        }


    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException, SQLException, ClassNotFoundException {
       if (itemDAO.existByPk(itemDTO.getItemCode())) throw new DuplicateException("ItemDTO Already Saved!!");
       itemDAO.save(convertor.toItem(itemDTO));
       return itemDTO;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException, SQLException, ClassNotFoundException {
        if (!itemDAO.existByPk(itemDTO.getItemCode())) throw new NotFoundException("ItemDTO not Found!!!");
        itemDAO.update(convertor.toItem(itemDTO));
        return itemDTO;
    }

    @Override
    public boolean deleteItem(String ItemCode) throws NotFoundException, InUseException, SQLException, ClassNotFoundException {
    if (itemDAO.existByPk(ItemCode)) throw new NotFoundException("ItemDTO not found!!");
    try{
        itemDAO.deleteByPk(ItemCode);
    }catch (ConstraintViolationException e){
        throw new InUseException("item Already in!!!!");
    }

        return false;
    }

    @Override
    public ItemDTO findByItemId(String ItemCode) throws lk.ijse.Fusion.service.exception.NotFoundException, SQLException, ClassNotFoundException {
        System.out.println("\n\n\nin item service impl\n\n\n");
        return convertor.fromItem(itemDAO.findByPk(ItemCode));
    }


}

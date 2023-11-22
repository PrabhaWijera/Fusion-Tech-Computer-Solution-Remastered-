package lk.ijse.Fusion.controller.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javassist.NotFoundException;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.custom.impl.ItemDAOimpl;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.ItemService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.mapping.Map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ItemForm {
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
    public ItemService itemService;

    @FXML
    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String ItemCode =item_ccodeTxt.getText();
        String ItemName = item_nameTxt.getText();
        String ItemDescription =  descriptionTxt.getText();
        double ItemPrice = Double.parseDouble(item_priceTxt.getText());
        int  QtyOnHand = Integer.parseInt( QTYTxt.getText());
        String  ItemType =   itemtypeTxt.getText();

        ItemDTO itemdto = new ItemDTO(ItemCode,ItemName,ItemDescription,ItemPrice,QtyOnHand,ItemType);

        itemService.saveItem(itemdto);

        if (itemdto !=null) {
            new Alert(Alert.AlertType.CONFIRMATION, "ItemDTO Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
        initialize();
    }

    public void updateOnAction(ActionEvent actionEvent) {
       String ItemCode =item_ccodeTxt.getText();
        String  ItemName = item_nameTxt.getText();
        String ItemDescription = descriptionTxt.getText();
        double ItemPrice = Double.parseDouble(item_priceTxt.getText());
        int  QtyOnHand = Integer.parseInt(QTYTxt.getText());
        String ItemType =itemtypeTxt.getText();


        ItemDTO itemdto = new ItemDTO(ItemCode,ItemName,ItemDescription,ItemPrice,QtyOnHand,ItemType);

        try {
            itemService.updateItem(itemdto);
            if (itemdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "ItemDTO Update!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String ItemCode =item_ccodeTxt.getText();
        String  ItemName = item_nameTxt.getText();
        String ItemDescription = descriptionTxt.getText();
        double ItemPrice = Double.parseDouble(item_priceTxt.getText());
        int  QtyOnHand = Integer.parseInt(QTYTxt.getText());
        String ItemType =itemtypeTxt.getText();


       ItemDTO itemdto = new ItemDTO(ItemCode,ItemName,ItemDescription,ItemPrice,QtyOnHand,ItemType);

        try {
            itemService.deleteItem(ItemCode);
            boolean c=itemService.deleteItem(ItemCode);
            if (itemdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "ItemDTO Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
       String  ItemCode = item_ccodeTxt.getText();
        try {
            ItemDTO itemdto = itemService.findByItemId(ItemCode);
            if (itemdto != null) {
                fillData(itemdto);
                //new Alert(Alert.AlertType.CONFIRMATION, "Search Ok!!!!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void fillData(ItemDTO itemdto) {
        item_ccodeTxt.setText(itemdto.getItemCode());
        itemtypeTxt.setText(itemdto.getItemType());
        item_nameTxt.setText(itemdto.getItemName());
        item_priceTxt.setText(String.valueOf(itemdto.getItemPrice()));
        descriptionTxt.setText(itemdto.getItemDescription());
        QTYTxt.setText(String.valueOf(itemdto.getQtyOnHand()));

    }

    public void initialize() throws SQLException, ClassNotFoundException {
        itemService= ServiceFactory.getInstance().getService(ServiceType.ITEM);
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




    public void itemCodeKeyOnRelese(KeyEvent keyEvent) {
     /*   Pattern idpattern=Pattern.compile("");
        boolean match=idpattern.matcher(item_ccodeTxt.getText()).matches();
        if(match){
            item_ccodeTxt.setStyle("-fx-border-color: lightgreen");
            addbtn.setDisable(false);
        }else{
            item_ccodeTxt.setStyle("-fx-border-color: red");
            addbtn.setDisable(true);

        }*/
    }


    public void itemnameKeyOnRelese(KeyEvent keyEvent) {
   /*     Pattern nameptn=Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

        boolean match=nameptn.matcher(item_nameTxt.getText()).matches();
        if(match){
            item_nameTxt.setStyle("-fx-border-color: lightgreen");
            addbtn.setDisable(false);
        }else{
            item_nameTxt.setStyle("-fx-border-color: red");
            addbtn.setDisable(true);
        }*/
    }

    public void itemPriceKeyOnRelese(KeyEvent keyEvent) {

    }

    public void DescriptionKeyOnRelese(KeyEvent keyEvent) {

    }

    public void QtyKeyOnRelese(KeyEvent keyEvent) {
    }

    public void itemtypeKeyOnRelese(KeyEvent keyEvent) {
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
}

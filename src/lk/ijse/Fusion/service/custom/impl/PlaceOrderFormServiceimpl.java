package lk.ijse.Fusion.service.custom.impl;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.dao.custom.impl.CustomerDAOimpl;
import lk.ijse.Fusion.dao.custom.impl.ItemDAOimpl;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.dto.OrdersDTO;
import lk.ijse.Fusion.dao.custom.impl.OrderDAOimpl;
import lk.ijse.Fusion.service.custom.PlaceOrderFromService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.tm.PlaceOrderTm;
import lk.ijse.Fusion.dto.CustomerDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormServiceimpl  {


    public AnchorPane pane;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public JFXComboBox cmbItemtype;
    public Label lblNic;
    public Label lblAddress;
    public Label lblPhoneNo;
    public Label lblEmail;
    public Label lblItemName;


    @FXML
    public Label orderIDlbl;
    public TextField payment;

    public Label totLBL;
    public Label BLLBL;
    private final ObservableList<PlaceOrderTm> obList= FXCollections.observableArrayList();
/*

    public void initialize(URL location, ResourceBundle resources) {

       loadCustomerId();
       loadItemId();
       loadItemType();
       setCellValueFactory();
        loadNextOrderId();
    }
*/
   private void loadNextOrderId() {
        try {
            String OrderID = OrderDAOimpl.generateNextOrderId();
//            lblOrderId.setText(OrderID);
            orderIDlbl.setText(OrderID);
            System.out.println(OrderID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerId() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = CustomerDAOimpl.loadCustomerId();
            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemId() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemDAOimpl.loadItemId();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemType() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> itemList = ItemDAOimpl.loadItemTypes();

            for (String code : itemList) {
                observableList.add(code);
            }
            cmbItemtype.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String code = String.valueOf(cmbCustomerId.getValue());
        try {
            CustomerDTO customerdto = CustomerDAOimpl.search(code);
            fillItemFields(customerdto);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(CustomerDTO customerdto) {
        lblCustomerName.setText(customerdto.getCustomerName());
        lblNic.setText(customerdto.getCustomerNIC());
        lblAddress.setText(customerdto.getAddress());
        lblPhoneNo.setText(customerdto.getPhoneNumber());
        lblEmail.setText(customerdto.getEmail());

    }

    public void cmbItemOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        try {
            ItemDTO itemdto = ItemDAOimpl.search(code);
            fillItemFields(itemdto);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(ItemDTO itemdto) {
        lblItemName.setText(itemdto.getItemName());
        lblDescription.setText(String.valueOf(itemdto.getItemDescription()));
        lblUnitPrice.setText(String.valueOf(itemdto.getItemPrice()));
        lblQtyOnHand.setText(String.valueOf(itemdto.getQtyOnHand()));
    }


    public void btnAddToCartOnAction(ActionEvent actionEvent) {
            String code = String.valueOf(cmbItemCode.getValue());
            String desc = lblDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            double total = unitPrice * qty;
            Button btnDelete = new Button("Delete");


            txtQty.setText("");

            if (!obList.isEmpty()) {
                L1:
                /* check same item has been in table. If so, update that row instead of adding new row to the table */
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (colItemCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = unitPrice * qty;
                        obList.get(i).setQty(qty);
                        obList.get(i).setTotal(total);
                        tblOrderCart.refresh();
                        calculateTotal();
                        return;
                    }
                }
            }

            btnDelete.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    PlaceOrderTm tm = (PlaceOrderTm) tblOrderCart.getSelectionModel().getSelectedItem();
                    tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
                    calculateTotal();
                }
            });

            obList.add(new PlaceOrderTm(code,unitPrice,qty,desc,total, btnDelete));
            tblOrderCart.setItems(obList);
            calculateTotal();
        }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {
        OrdersDTO ordersdto =new OrdersDTO(
                orderIDlbl.getText(),
                (String.valueOf(LocalDate.now())),
                (String.valueOf(cmbCustomerId.getValue()))
        );

        ArrayList<OrderDetailDTO> details=new ArrayList<>();
        for (PlaceOrderTm tm:obList
        ) {
            details.add(new OrderDetailDTO(
                    orderIDlbl.getText(),
                    tm.getCode(),
                    (String.valueOf(tm.getQty())),
                    (String.valueOf(tm.getUnitPrice()))
            ));
        }

        Connection connection=null;


        try{
            connection=  DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSaved=new OrderDAOimpl().saveOrder(ordersdto);
            if(isOrderSaved){
                boolean isDetailsSaved=new OrderDAOimpl().saveOrderDetails(details);
                if(isDetailsSaved){
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }finally {
            connection.setAutoCommit(true);
        }
        totalBalance();
        loadNextOrderId();
    }




    public void txtQtyOnAction(ActionEvent actionEvent) {}


    public void ReportOnAaction(ActionEvent actionEvent) {

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/lk/ijse/Fusion/View/Reports/Blank.jrxml"));
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

    private int calculateTotal(){
        int total=0;
        for(PlaceOrderTm tm: obList){
            total+=tm.getTotal();
        }
        totLBL.setText(String.valueOf(total));
        return total;
    }

private void totalBalance(){
        int CustomerPay=Integer.valueOf(payment.getText());
        int balance=CustomerPay-calculateTotal();
    BLLBL.setText(String.valueOf(balance));
}


    public OrderDetailDTO saveorder(OrderDetailDTO orderDetailDTO) throws DuplicateException {
        return null;
    }


    public OrderDetailDTO updateOrder(OrderDetailDTO orderDetailDTO) throws NotFoundException {
        return null;
    }


    public void deleteOrder(String OrderId) throws NotFoundException, SQLException, InUseException, ClassNotFoundException {

    }




/*
    //catch the report
    JasperReport compileReport=(JasperReport) JRLoader loadObject(this.getClass().getResource(""/lk/ijse/Fusion/View/Reports/Fusion_plzOrder.jasper"));


            //3STEP fill the informationn which reprt needed
            JasperPrint jasperPrint=  JasperFillManager.fillReport(compileReport,null,new JREmptyDataSource(1));
            //4STEP----


            JasperViewer.viewReport(jasperPrint,false);





 */
}



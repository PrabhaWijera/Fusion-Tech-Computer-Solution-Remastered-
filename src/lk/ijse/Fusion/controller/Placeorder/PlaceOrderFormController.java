package lk.ijse.Fusion.controller.Placeorder;

import com.jfoenix.controls.JFXComboBox;
import com.mongodb.DBPortPool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.Navigation;
import lk.ijse.Fusion.Util.Routes;
import lk.ijse.Fusion.dao.custom.impl.CustomerDAOimpl;
import lk.ijse.Fusion.dto.ItemDTO;
import lk.ijse.Fusion.dao.custom.impl.ItemDAOimpl;
import lk.ijse.Fusion.dao.custom.impl.OrderDAOimpl;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.*;
import lk.ijse.Fusion.tm.PlaceOrderTm;
import lk.ijse.Fusion.dto.CustomerDTO;
import lk.ijse.Fusion.dto.OrderDetailDTO;
import lk.ijse.Fusion.dto.OrdersDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class PlaceOrderFormController implements Initializable {


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
    public CustomerService  customerService;
    public ItemService itemService;

    @FXML
    public Label orderIDlbl;
    public TextField payment;

    public Label totLBL;
    public Label BLLBL;
    public OrdersService ordersService;
    public PlaceOrderService placeOrderService;
    public OrderDetailService orderDetailService;

    private final ObservableList<PlaceOrderTm> obList= FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {
        placeOrderService=ServiceFactory.getInstance().getService(ServiceType.PLZ);
        ordersService=ServiceFactory.getInstance().getService(ServiceType.ORDER);
        orderDetailService=ServiceFactory.getInstance().getService(ServiceType.ORDERDETAILS);
        customerService= ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    itemService=ServiceFactory.getInstance().getService(ServiceType.ITEM);
       loadCustomerId();
       loadItemId();
       loadItemType();
       setCellValueFactory();
        loadNextOrderId();

    }

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
        String s = cmbCustomerId.getItems().toString();

        try {
//            System.out.println("code: " + code);
//            System.out.println("customer dto: ");
            CustomerDTO customerdto = customerService.findByCustomerId(code);
            if (customerdto!=null){
                fillItemFields(customerdto);
                txtQty.requestFocus();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(CustomerDTO customerdto) {
        System.out.println("check1"+customerdto.getCustomerName());
        lblCustomerName.setText(customerdto.getCustomerName());
        lblNic.setText(customerdto.getCustomerNIC());
        lblAddress.setText(customerdto.getAddress());
        lblPhoneNo.setText(customerdto.getPhoneNumber());
        lblEmail.setText(customerdto.getEmail());

    }

    public void cmbItemOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());

        try {
            ItemDTO itemdto = itemService.findByItemId(code);
       //     ItemDTO itemdto=itemService
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
            int qty = parseInt(txtQty.getText());
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

public void Transaction() throws SQLException {
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
     //  obList.add(tm);
        boolean isAdd=placeOrderService.getmangeOrder(details,ordersdto);
        if(isAdd){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
        }
        tblOrderCart.setItems(obList);
    }


/*
    Connection connection=null;
    try{
        connection=  DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        boolean isOrderSaved=ordersService.saveOrder(ordersdto);
        if(isOrderSaved){
            boolean isDetailsSaved=orderDetailService.saveOrderDetails(details);
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
    }catch(SQLException e){
        System.out.println(e);
    }finally {
        connection.setAutoCommit(true);
    }*/
    totalBalance();
    loadNextOrderId();
}
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {
    Transaction();

    }


    public void txtQtyOnAction(ActionEvent actionEvent) {}


    public void ReportOnAaction(ActionEvent actionEvent) throws IOException, InterruptedException {

        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/Resouces/View/Reports/Blank.jrxml"));
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
        new Alert(Alert.AlertType.CONFIRMATION,"Bill Genarating!!").show();
        Navigation.navigate(Routes.PLACEORDER,pane);

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
        int CustomerPay= Integer.parseInt(payment.getText());
        int balance=CustomerPay-calculateTotal();
    BLLBL.setText(String.valueOf(balance));
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



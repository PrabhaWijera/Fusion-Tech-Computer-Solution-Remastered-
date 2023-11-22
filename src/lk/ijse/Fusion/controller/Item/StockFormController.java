package lk.ijse.Fusion.controller.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Fusion.Database.DBConnection;
import lk.ijse.Fusion.Util.CRUDutil;
import lk.ijse.Fusion.dao.DaoFactory;
import lk.ijse.Fusion.dao.DaoTypes;

import lk.ijse.Fusion.dao.custom.Stockdao;
import lk.ijse.Fusion.dao.custom.impl.StockDAOimpl;
import lk.ijse.Fusion.dto.StockDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.StockService;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockFormController {
    public AnchorPane StockContext;

    public TextField stockIdTxt;
    public TextField StockqtyTxt;

    public TextField dateTxt;
    public TableView stockTbl;

    public TableColumn ColStockId;
    public TableColumn ColItemCode;
    public TableColumn ColQty;
    public TableColumn ColDate;
    public TextField itemCodeTxt;
    private final Convertor convertor;
    private final Connection connection;
    private  final Stockdao stockDAO;
    public StockService stockService;



public StockFormController() throws SQLException, ClassNotFoundException {
    connection=DBConnection.getInstance().getConnection();
    stockDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.STOCK);
    convertor=new Convertor();
}

    public void initialize() throws SQLException, ClassNotFoundException {
    stockService= ServiceFactory.getInstance().getService(ServiceType.STOCK);
        ColStockId.setCellValueFactory(new PropertyValueFactory("StockDTO ID"));
        ColQty.setCellValueFactory(new PropertyValueFactory("QTY"));
        ColDate.setCellValueFactory(new PropertyValueFactory("Date"));
        try {
            loadAllStock();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }

}
    public void Stock_Manage_EmployeeOnAction(ActionEvent actionEvent) {
    }

    public void Stock_ItemDetails_OnAction(ActionEvent actionEvent) {
    }

    public void addOnAction(ActionEvent actionEvent) {
         String StockID = stockIdTxt.getText();
       String  StockDate = dateTxt.getText();
        int stockQty = Integer.parseInt(StockqtyTxt.getText());
      StockDTO stockdto = new StockDTO(StockID,StockDate, stockQty);
        try {
            stockService.saveStock(stockdto);
            if (stockdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "StockDTO Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void editonAction(ActionEvent actionEvent) {
        String StockID = stockIdTxt.getText();
        String  StockDate = dateTxt.getText();
        int stockQty = Integer.parseInt(StockqtyTxt.getText());
       StockDTO stockdto = new StockDTO(StockID,StockDate, stockQty);

         try {
             stockService.updateStock(stockdto);
            if (stockdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "StockDTO Update!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteeOnAction(ActionEvent actionEvent) {
      String StockID = stockIdTxt.getText();
        String  StockDate = dateTxt.getText();

        int stockQty = Integer.parseInt(StockqtyTxt.getText());

        StockDTO stockdto = new StockDTO(StockID,StockDate, stockQty);
        try {
            stockService.deleteStock(StockID);

            if (stockdto !=null) {
                new Alert(Alert.AlertType.CONFIRMATION, "StockDTO Delete!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ReloadOnAction(ActionEvent actionEvent) {

    }

    private void loadAllStock() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDutil.execute("SELECT * FROM  Stock");
        ObservableList<StockDTO> observableList = FXCollections.observableArrayList();
        while(resultSet.next()){
            observableList.add(
                    new StockDTO(
                            resultSet.getString("StockID"),
                            resultSet.getString("StockDate"),
                            resultSet.getInt("stockQty")
                    )
            );
        }
        stockTbl.setItems(observableList);
    }


}

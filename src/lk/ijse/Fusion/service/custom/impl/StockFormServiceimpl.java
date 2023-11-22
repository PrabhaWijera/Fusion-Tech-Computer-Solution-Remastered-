package lk.ijse.Fusion.service.custom.impl;

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
import lk.ijse.Fusion.dao.exception.ConstraintViolationException;
import lk.ijse.Fusion.dto.StockDTO;
import lk.ijse.Fusion.service.ServiceFactory;
import lk.ijse.Fusion.service.ServiceType;
import lk.ijse.Fusion.service.custom.StockService;
import lk.ijse.Fusion.service.exception.DuplicateException;
import lk.ijse.Fusion.service.exception.InUseException;
import lk.ijse.Fusion.service.exception.NotFoundException;
import lk.ijse.Fusion.service.util.Convertor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockFormServiceimpl implements StockService {
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
    private  final Convertor convertor;
    private  final Connection connection;
    private  Stockdao stockDAO;

    public StockFormServiceimpl()  {
        connection= DBConnection.getInstance().getConnection();
        stockDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.STOCK);
        convertor=new Convertor();
        //stockDAO= ServiceFactory.getInstance().getService(ServiceType.STOCK);

    }


    public void initialize(){
        ColStockId.setCellValueFactory(new PropertyValueFactory("StockDTO ID"));
        ColQty.setCellValueFactory(new PropertyValueFactory("QTY"));
        ColDate.setCellValueFactory(new PropertyValueFactory("Date"));
        try {
            loadAllStock();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }

}
    public void ReloadOnAction(ActionEvent actionEvent) {
    initialize();
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


    @Override
    public StockDTO saveStock(StockDTO stockDTO) throws DuplicateException, SQLException, ClassNotFoundException {
        if (stockDAO.existByPk(stockDTO.getStockID()))throw new DuplicateException("StockDTO Already add!!!!");
        stockDAO.save(convertor.toStock(stockDTO));
        return stockDTO;
    }

    @Override
    public StockDTO updateStock(StockDTO stockDTO) throws NotFoundException, SQLException, ClassNotFoundException {
        if (!stockDAO.existByPk(stockDTO.getStockID())) throw new NotFoundException("StockDTO not found!!");
        stockDAO.update(convertor.toStock(stockDTO));
        return stockDTO;
    }

    @Override
    public void deleteStock(String StockID) throws NotFoundException, InUseException, SQLException {
        if (! stockDAO.existByPk(StockID))throw new NotFoundException("StockDTO Not Found!!!!");
        try{
            stockDAO.deleteByPk(StockID);
        }catch (ConstraintViolationException | ClassNotFoundException e){
            throw new InUseException("StockDTO Already In!!!");
        }
    }
}

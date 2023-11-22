package lk.ijse.Fusion.dto;

import java.util.ArrayList;

public class StockDTO {
    private String StockID;
    private String  StockDate;
    private int stockQty;
    private ArrayList<StockDTO> stockdtoDetail;


    public String getStockID() {
        return StockID;
    }

    public void setStockID(String stockID) {
        StockID = stockID;
    }

    public String getStockDate() {
        return StockDate;
    }

    public void setStockDate(String stockDate) {
        StockDate = stockDate;
    }



    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }





    public StockDTO(String stockID, String stockDate, int stockQty) {
       this.StockID = stockID;
       this.StockDate = stockDate;

       this.stockQty=stockQty;
    }

    public StockDTO() {
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "StockID='" + StockID + '\'' +
                ", StockDate=" + StockDate +

                ", stockQty=" + stockQty +

                '}';
    }



}

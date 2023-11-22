package lk.ijse.Fusion.dao;



import lk.ijse.Fusion.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DaoTypes daoType) {
        switch (daoType){
            case ATTENDS:
                return (T)new AttendsDAOimpl(connection);

            case CUSTOMER:
                return (T)new CustomerDAOimpl(connection);

            case EMPLOYEE:
                return (T)new EmployeeDAOimpl(connection);

            case ITEM:
                return (T) new ItemDAOimpl(connection);

           case ORDER:
                return (T)new OrderDAOimpl(connection);

            case SALARY:
                return (T)new SalaryDAOimpl(connection);

            case STOCK:
                return (T)new StockDAOimpl(connection);

            case SUPPLIERS:
                return (T)new SuppliersDAOimpl(connection);
            default:
                return null;
        }
    }
}

package lk.ijse.Fusion.service;

import lk.ijse.Fusion.service.custom.OrderDetailService;
import lk.ijse.Fusion.service.custom.OrdersService;
import lk.ijse.Fusion.service.custom.SuppliersService;
import lk.ijse.Fusion.service.custom.impl.*;
import rex.utils.S;

import java.sql.SQLException;

public class ServiceFactory {
    private static  ServiceFactory serviceFactory;

    private ServiceFactory(){

    }
    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }
    public <T extends SuperService> T getService(ServiceType serviceType) {
        switch (serviceType){
            case CUSTOMER:
                return (T) new CustomerServiceimpl();

            case ATTENDS:
                return (T) new AttendsServiceimpl();

            case EMPLOYEE:
                return (T) new EmployeeServiceimpl();

            case ITEM:
                return (T) new ItemFormServiceimpl();

            case ORDERDETAILS:
                return (T) new OrderDetailServiceimpl();

            case ORDER:
                return (T) new OrderServiceimpl();

            case SALARY:
                return (T) new SalaryFormServiceimpl();

            case STOCK:
                return (T) new StockFormServiceimpl();

            case SUPPLIER:
                return (T) new SuppliersServiceimpl();

            case PLZ:
                return (T) new PlaceOrderServiceimpl();
            default:
                return null;
        }
    }
}

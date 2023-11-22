package lk.ijse.Fusion.service.util;


import lk.ijse.Fusion.dto.*;
import lk.ijse.Fusion.entity.*;

public class Convertor {

    public AttendenceDTO fromAttends(Attendence attendence){
        return new AttendenceDTO(attendence.getAttendID(),
                attendence.getAttendDate(),
                attendence.getInTime(),
                attendence.getOutTime());

    }

    public Attendence toAttends(AttendenceDTO attendsDTO){
        return new Attendence(attendsDTO.getAttendID(), attendsDTO.getAttendDate(), attendsDTO.getInTime(), attendsDTO.getOutTime());
    }

    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomerID(),customer.getCustomerNIC(),customer.getCustomerName(),customer.getAddress(),customer.getPhoneNumber(),customer.getEmail());
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomerID(),customerDTO.getCustomerNIC(),customerDTO.getCustomerName(),customerDTO.getAddress(),customerDTO.getPhoneNumber(),customerDTO.getEmail());
    }

    public EmployeeDTO fromEmployee(Employee employee){
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeNIC(),employee.getName(),employee.getAddress(),employee.getEmail(),employee.getMobile(),employee.getJobROLE(),employee.getDOB(),employee
                .getJoinDate(),employee.getGender());
    }

    public Employee toEmployee(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getEmployeeId(),employeeDTO.getEmployeeNIC(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getEmail(),employeeDTO.getMobile(),employeeDTO.getJobROLE(),employeeDTO.getDOB(),employeeDTO
                .getJoinDate(),employeeDTO.getGender());
    }

    public ItemDTO fromItem(Item item){
        return new ItemDTO(item.getItemCode(),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getQtyOnHand(),
                item.getItemType());
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemDescription(),itemDTO.getItemPrice(),itemDTO.getQtyOnHand(),itemDTO.getItemType());
    }


    public OrderDetailDTO fromOrderDetails(OrderDetail orderDetail){
        return new OrderDetailDTO(orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getQty(),orderDetail.getUnitPrice());
    }

    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO){
        return new OrderDetail(orderDetailDTO.getOrderId(),orderDetailDTO.getItemCode(),orderDetailDTO.getQty(),orderDetailDTO.getUnitPrice());
    }


    public OrdersDTO fromOrder(Orders ordersdto){
        return new OrdersDTO(ordersdto.getOrderID(),ordersdto.getOrderDate(),ordersdto.getCustomerID());
    }

    public Orders toOrder(OrdersDTO orderDTO){
        return new Orders(orderDTO.getOrderID(),orderDTO.getOrderDate(),orderDTO.getCustomerID());
    }

    public SalaryDTO fromSalary(Salary salary){
        return new SalaryDTO(salary.getSalaryID(),salary.getEmployeeID(),salary.getSalaryAmount(),salary.getSalaryMethod(),salary.getDetails());
    }
    public Salary toSalary(SalaryDTO salaryDTO){
        return new Salary(salaryDTO.getSalaryID(),salaryDTO.getEmployeeID(),salaryDTO.getSalaryAmount(),salaryDTO.getSalaryMethod(),salaryDTO.getDetails());
    }
    public StockDTO fromStock(Stock stock){
        return new StockDTO(stock.getStockID(),stock.getStockDate(),stock.getStockQty());
    }

    public Stock toStock(StockDTO stockDTO){
        return new Stock(stockDTO.getStockID(),stockDTO.getStockDate(),stockDTO.getStockQty());
    }


    public SuppliersDTO fromSuppliers(Suppliers suppliers){
        return new SuppliersDTO(suppliers.getSupplierID(),
                suppliers.getSupplierName(),
                suppliers.getSupplierEmail(),
                suppliers.getSupplierContact(),
                suppliers.getPrice(),
                suppliers.getItemCode(),
                suppliers.getAmount());
    }

    public Suppliers toSuppliers(SuppliersDTO suppliersDTO){
        return new Suppliers(suppliersDTO.getSupplierID(),suppliersDTO.getSupplierName(),suppliersDTO.getSupplierEmail(),suppliersDTO.getSupplierContact(),suppliersDTO.getPrice(),suppliersDTO.getItemCode(),suppliersDTO.getAmount());
    }
}

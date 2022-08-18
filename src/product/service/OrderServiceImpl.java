package product.service;

import product.domain.customer.Customer;
import product.domain.customer.CustomerDao;
import product.domain.orders.OrderDao;
import product.domain.product.ProductDao;
import product.dto.OrderRespDto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Currency;

//임플리먼츠하면 오류남. 재정의하라고.
public class OrderServiceImpl implements OrderService{

    // 컴포지션 필요
    private Connection connection;
    private ProductDao productDao;
    private OrderDao orderDao;
    private CustomerDao customerDao;

    public OrderServiceImpl(Connection connection, ProductDao productDao, OrderDao orderDao) {
        this.connection = connection;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @Override
    public void 주문하기(int customerId, int productId) {
        // 1. 재고확인(재고가 0보다 큰가? product findById)
        // 2. 주문하기(order insert)
        // 3. 재고 -1 하기 (product updateByidToQTY)
    }

    @Override
    public void 주문취소하기(int id) {
        // 1. order deleteById
        // 2. product qty +1(update)
    }

    @Override
    public ArrayList<OrderRespDto> 고객별주문목록보기(int customerId) {
        return orderDao.findByIdToOrderList(customerId);
    }
}

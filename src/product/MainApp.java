package product;


import product.DB.DBConnection;
import product.domain.customer.CustomerDao;
import product.domain.orders.OrderDao;
import product.domain.product.ProductDao;
import product.dto.OrderRespDto;
import product.service.OrderService;
import product.service.OrderServiceImpl;

import java.sql.Connection;
import java.util.ArrayList;

// 사실상 소캣 통신하니까 앞으로 얘는 필요 없음
public class MainApp {
    public static void main(String[] args) {
        Connection conn = DBConnection.connection();

        CustomerDao customerDao = new CustomerDao(conn);
        ProductDao productDao = new ProductDao(conn);
        OrderDao orderDao = new OrderDao(conn);

        OrderService orderService = new OrderServiceImpl(conn, productDao, orderDao);

        ArrayList<OrderRespDto> orderRespDtoArrayList = orderService.고객별주문목록보기(1);
    }
}

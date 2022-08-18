package product.service;

import product.dto.OrderRespDto;

import java.util.ArrayList;

public interface OrderService {

    public void 주문하기(int customerId, int productId);
    public void 주문취소하기(int id);
    public ArrayList<OrderRespDto> 고객별주문목록보기(int customerId);

}

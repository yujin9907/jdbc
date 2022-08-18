package product.domain.orders;

import product.dto.OrderRespDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDao {

    private Connection conn;

    public OrderDao(Connection conn) {
        this.conn = conn;
    }

    //    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Orders> findAll(){
        ArrayList<Orders> orderList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setId(rs.getInt("id"));
                orders.setCustomerId(rs.getInt("customerId"));
                orders.setProductId(rs.getInt("productId"));

                orderList.add(orders);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return orderList;
    }


    public Orders findById(int id){
        Orders orders = new Orders();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                orders.setId(rs.getInt("id"));
                orders.setCustomerId(rs.getInt("getCustomerId"));
                orders.setProductId(rs.getInt("getProductId"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return orders;
    }


    public int insert(Orders orders){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO orders ");
            sql.append("VALUES(orders_seq.nextval, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, orders.getCustomerId());
            ps.setInt(2, orders.getProductId());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 컬럼을 다 적어줄 수 없으니 웨어절에 걸리는 id 빼고 나머지는 커스터머로
    public int updateById(int id, Orders orders){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE orders SET customerId = ?, productId = ?");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, orders.getCustomerId());
            ps.setInt(2, orders.getProductId());
            ps.setInt(3, orders.getId());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(int id){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM orders WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // 기술면접대비 dto에 대한 개념과 필요성 정리
    public ArrayList<OrderRespDto> findByIdToOrderList(int customerId){
        // 뭐를 반환해야 되냐... 무슨 타입을 반환해야되냐...
        // 프로젝션 된 애처럼 된 걸 말해야 되는데...
        // dto > 엔티티는 아닌데 조인과 같은 결과로 나온 데이터 모임
        ArrayList<OrderRespDto> dtoList = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select o.id, c.username, p.name, p.price");
            sql.append("from orders o");
            sql.append("INNER JOIN customer c");
            sql.append("ON o.customerId = c.id");
            sql.append("INNER JOIN product p");
            sql.append("ON o.productId = p.id");
            sql.append("WHERE c.id = ?");
            // 존나 너무 기니까 좀 끊어서

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                OrderRespDto dto = new OrderRespDto();
                dto.setId(rs.getInt("id"));
                dto.setUsername(rs.getString("username"));
                dto.setName(rs.getString("name"));
                dto.setPrice(rs.getInt("price"));

                dtoList.add(dto);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dtoList;
    }

}

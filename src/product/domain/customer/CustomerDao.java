package product.domain.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDao {

    private Connection conn;

    public CustomerDao(Connection conn) {
        this.conn = conn;
    }

//    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Customer> findAll(){
        ArrayList<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("getUsername"));
                customer.setPassword(rs.getString("getPassword"));
                customer.setEmail(rs.getString("getEmail"));

                customerList.add(customer);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return customerList;
    }


    public Customer findById(int id){
        Customer customer = new Customer();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("getUsername"));
                customer.setPassword(rs.getString("getPassword"));
                customer.setEmail(rs.getString("getEmail"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return customer;
    }


    public int insert(Customer customer){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO customer ");
            sql.append("VALUES(customer_seq.nextval, ?, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    return result;
    }

    // 컬럼을 다 적어줄 수 없으니 웨어절에 걸리는 id 빼고 나머지는 커스터머로
    public int updateById(int id, Customer customer){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE customer username = ?, password = ?, email = ?");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getEmail());
            ps.setInt(4, customer.getId());

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
            sql.append("DELETE FROM customer WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

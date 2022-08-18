package product.domain.product;

import product.domain.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDao {

    private Connection conn;

    public ProductDao(Connection conn) {
        this.conn = conn;
    }

    //    한건추가,한건삭제, 업데이트, 목록보기, 하나보기
    public ArrayList<Product> findAll(){
        ArrayList<Product> productList = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("getname"));
                product.setPrice(rs.getInt("getPrice"));
                product.setQty(rs.getInt("getQty"));

                productList.add(product);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }


    public Product findById(int id){
        Product product = new Product();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                product.setName(rs.getString("getname"));
                product.setPrice(rs.getInt("getPrice"));
                product.setQty(rs.getInt("getQty"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return product;
    }


    public int insert(Product product){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO product ");
            sql.append("VALUES(product_seq.nextval, ?, ?, ?)");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getQty());

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int updateById(String name, String price, String qty, int id){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE product SET name = ?, price = ?, qty = ?");
            sql.append("WHERE id=?");


            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, qty);
            ps.setInt(4, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int delete(int id){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM product WHERE id = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int updateByIdToQTY(int id){
        int result = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE product SET qty = qty -1");
            sql.append("WHERE id=?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, id);

            result = ps.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

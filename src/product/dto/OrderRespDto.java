package product.dto;
// 통신을 위한 데이터 모임
// 정해진 엔티티가 없는 데이터들의 모임은 엔티티로 받을 수 없기 때문에
// 그 대안으로 만드는 곳
public class OrderRespDto {
    // 쿼리 적은 거에 프로젝션만 붙여주면 됨
    private int id;
    private String username;
    private String name;
    private  int price;

    public OrderRespDto() {
    }

    public OrderRespDto(int id, String username, String name, int price) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

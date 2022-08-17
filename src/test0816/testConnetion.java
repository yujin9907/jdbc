package test0816;

import service.DBConnection;

import java.sql.Connection;

public class testConnetion {
    public static void main(String[] args) {
        Connection conn1 = DBConnection.connection();
        Connection conn2 = DBConnection.connection();

        // 모든 클래스는 object 클래스를 상속함(extends를 따로 안 해도)
        // 오브젝트는 해시코드를 지원해줌=메모리주소를 리턴해줌(실제 메모리 주소는 아니고 메모리주소를 해시화해서)

        System.out.println(conn1.hashCode());
        System.out.println(conn2.hashCode());

    }
}

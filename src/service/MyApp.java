package service;

import java.sql.Connection;
import java.util.ArrayList;

public class MyApp {
    public static void main(String[] args) {

        Connection conn = DBConnection.connection();

        EmpDao empDao = new EmpDao(conn);
        DeptDao deptDao = new DeptDao(conn);

        // 생성자 주입 (서비스는 이제 em와 de의 힙공간에 접근이 가능함)
        MyService s = new MyService(empDao, deptDao, conn);

        //given ; 샘플로 만들어서 테스트 해보기
        Dept dept = new Dept(50, "TF팀","부산");
        ArrayList<Integer> empno = new ArrayList<>();
        empno.add(7369);
        empno.add(7499);

    }
}

package dbex;

import java.util.ArrayList;

public class EmpApp {
    public static void main(String[] agrs) {
        EmpDao empDao = new EmpDao();
        //ArrayList<Emp> emps = empDao.직원목록보기();
        //Emp emp = empDao.직원한건보기(7369);

        // 생성자 초기화, 애초에 뉴와 동시에 값을 넣는 거 코드의 깔끔함을 위해
//        Emp emp = new Emp(
//                7777,
//                "metacoding",
//                "MANAGER",
//                7369,
//                500,
//                0,
//                10
//        );
//        empDao.직원추가(emp);
        int result = empDao.직원삭제(7777);
        System.out.println(result);
    }
}

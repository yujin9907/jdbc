package service;

import java.sql.Connection;
import java.util.ArrayList;

// 상속? 컴포지션? 컴포지션. 둘은 상속 관계가 아니라서
public class MyService {

    private EmpDao empDao;
    private DeptDao deptDao;
    private Connection conn;

    public MyService(EmpDao empDao, DeptDao deptDao, Connection conn) {
        this.empDao = empDao;
        this.deptDao = deptDao;
        this.conn = conn;
    }

    // 상태값, 초기화는 책임 분할을 위해 구현부에서

    // 1. 직원 목록 필요
    public ArrayList<Emp> 직원목록(){
        return empDao.직원목록보기();
    }
    // 2. 직원 하나 보기
    public Emp 직원한명(int e){
        return empDao.직원한건보기(e);
    }
    //3. 직원 추가
    public void 직원추가(Emp emp){
        int result = empDao.직원추가(emp);
        try {
            if(result==1){
                conn.commit();
            }else{
                conn.rollback();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //4. 부서 추가 ; 트랜잭션 관리 구현 아직 안 됨
    public void 부서추가(Dept dept){
        int result = deptDao.부서추가(dept);
        try {
            if(result==1){
                conn.commit();
            } else{
                conn.rollback();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 5. tf팀 창설 ; 동적 변경과 부서 인서트와 사원 변경의 서비스를 하는 메서드를 만들어야 됨
    public void tf팀창설(Dept dept, ArrayList<Integer> empnos){
        int count = 0;
        count = count + deptDao.부서추가(dept);
        for(int i=0;i<empnos.size();i++){
            count = count + empDao.직원수정(empnos.get(i), dept.getDeptno());
        }
        try {
           if(count==empnos.size()+1){
               conn.commit();
           } else{
               conn.rollback();
           }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


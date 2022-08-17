package service;

import java.sql.Timestamp;
// 테이블 만들기, 테이블 컬럼, 필요한 생성자+디폴트생성자, 게터세터
// 생성자 아니면 get.nsfldf 하면서 ㅅㅂ 일일이 해야 됨 구현할때
public class Emp {
    // 엔티티 생성
    // 자바에서 상태 변수를 만들 땐 무조건 private (이걸로 안 하면 변수에 막 접근 변경할 수 있기 때문에 행위로만 변경해야 됨)
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Timestamp hiredate;
    private int sal;
    private int comm;
    private int deptno;
    // 타임스탭 잘 안 씀 요즘엔

    // 생성자 만들기


    public Emp(int empno, String ename, String job, int mgr, int sal, int comm, int deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }
    public Emp(){
        // 디폴트 생성자가 있어야 다른 생성자를 생성할 수 있음
    }

    public int getEmpno() {
        return empno;
    }
    public void setEmpno(int empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public int getMgr() {
        return mgr;
    }
    public void setMgr(int mgr) {
        this.mgr = mgr;
    }
    public Timestamp getHiredate() {
        return hiredate;
    }
    public void setHiredate(Timestamp hiredate) {
        this.hiredate = hiredate;
    }
    public int getSal() {
        return sal;
    }
    public void setSal(int sal) {
        this.sal = sal;
    }
    public int getComm() {
        return comm;
    }
    public void setComm(int comm) {
        this.comm = comm;
    }
    public int getDeptno() {
        return deptno;
    }
    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }


}


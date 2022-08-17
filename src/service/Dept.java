package service;

public class Dept {
    private int detpno;
    private String dname;
    private String loc;

    public Dept(){

    }
    public Dept(int detpno, String dname, String loc) {
        this.detpno = detpno;
        this.dname = dname;
        this.loc = loc;
    }

    public int getDeptno() {
        return detpno;
    }

    public void setDeptno(int detpno) {
        this.detpno = detpno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }


}

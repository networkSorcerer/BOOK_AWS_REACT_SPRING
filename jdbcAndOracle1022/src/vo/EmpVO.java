package vo;
// vo(value Object) : 데이터 베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
// vo객체는 데이터 베이스 테이블의 각 컬럼에 해당하는 멤버 변수 (인스턴스 필드)를 만듦
// 일반적으로 데이터 베이스 테이블의 레코드 한행을 객체로 매핑하기 위해 사용
// vo 객체는 로직을 가지지 않고 순수하게 데이터를 전달하는 용도로 사용

import java.math.BigDecimal;
import java.sql.Date;


public class EmpVO {
    private int empNO; // 사원번호
    private String name; // 사원이름
    private String job;
    private int mgr;
    private Date date;
    private BigDecimal sal;
    private BigDecimal comm;
    private int deptNo;

    public EmpVO(int empNO, String name, String job, int mgr, Date date, BigDecimal sal, BigDecimal comm, int deptNo) {
        this.empNO = empNO;
        this.name = name;
        this.job = job;
        this.mgr = mgr;
        this.date = date;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public EmpVO() {
    }

    public int getEmpNO() {
        return empNO;
    }

    public void setEmpNO(int empNO) {
        this.empNO = empNO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
}

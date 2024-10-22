package dao;
// DAO(Database Acceess Object) :
// 데이터 베이스에 접근하여 데이터를 조회하거나 수정하는 데 사용
// DAO는 VO객체와 데이터베이스 간의 매핑을 담당

import common.Common;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class EmpDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner sc = null;

    public EmpDAO() {
        sc = new Scanner(System.in);
    }

    ;

    // select 기능(조회) 구현
    public List<EmpVO> empSelect() {
        List<EmpVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement();  // statement 생성
            String query = "SELECT * FROM EMP";
            // excuteQuery : select 문과 같이 결과 값이 여러 개의 레코드로 반환되는 경우 사용
            rs = stmt.executeQuery(query);
            // ResultSet : 여러 행의 결과를 받아서 반복자 제공
            // next() : 현재 행에서 한행 앞으로 이동
            // previous() :  현재 행에서 한행 뒤로 이동
            // first() : 첫번째 행으로 이동
            // last() : 마지막 행으로 이동
            while (rs.next()) {
                int empNo = rs.getInt("EMPNO");
                String name = rs.getString("ENAME");
                String job = rs.getString("JOB");
                int mgr = rs.getInt("MGR");
                Date date = rs.getDate("HIREDATE");
                BigDecimal sal = rs.getBigDecimal("SAL");
                BigDecimal comm = rs.getBigDecimal("COMM");
                int deptNo = rs.getInt("DEPTNO");
                EmpVO vo = new EmpVO(empNo, name, job, mgr, date, sal, comm, deptNo);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            System.out.println("EMP 조회 실패 !!!");
            e.printStackTrace();
        }
        return list;
    }

    // insert 구현
    public boolean empInsert(EmpVO vo) {
        String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) values(?,?,?,?,?,?,?,? )";
        try {
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getEmpNO());
            psmt.setString(2, vo.getName());
            psmt.setString(3, vo.getJob());
            psmt.setInt(4, vo.getMgr());
            psmt.setDate(5, vo.getDate());
            psmt.setBigDecimal(6, vo.getSal());
            psmt.setBigDecimal(7, vo.getComm());
            psmt.setInt(8, vo.getDeptNo());
            psmt.executeUpdate(); // insert, update, delete에 해당하는 함수
            return true;
        } catch (Exception e) {
            System.out.println("INSERT 실패 !!!!!");
            e.printStackTrace();
            return false;
        } finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }

    public boolean empUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 사원의 이름을 입력하세요: ");
        String name = sc.next();
        System.out.println("직책: ");
        String job = sc.next();
        System.out.println("급여: ");
        int sal = sc.nextInt();
        System.out.println("성과급: ");
        int comm = sc.nextInt();

        // 안전한 SQL 쿼리 (PreparedStatement 사용)
        String query = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE ENAME = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 데이터베이스 연결
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(query);

            // PreparedStatement 파라미터 설정
            pstmt.setString(1, job);  // 1번째 파라미터: job
            pstmt.setInt(2, sal);     // 2번째 파라미터: sal
            pstmt.setInt(3, comm);    // 3번째 파라미터: comm
            pstmt.setString(4, name); // 4번째 파라미터: ename

            // 쿼리 실행 및 결과 확인
            int ret = pstmt.executeUpdate();
            System.out.println("변경된 레코드 수: " + ret);

            if (ret > 0) {
                return true; // 업데이트 성공
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            Common.close(pstmt);
            Common.close(conn);
        }

        return false; // 업데이트 실패
    }

    public boolean empDelete(EmpVO vo) {
        String query = "DELETE FROM emp WHERE ename = ?";
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = Common.getConnection(); // Obtain a connection to the database
            psmt = conn.prepareStatement(query); // Prepare the SQL query
            psmt.setString(1, vo.getName()); // Set the name parameter

            int affectedRows = psmt.executeUpdate(); // Execute the update query
            return affectedRows > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            throw new RuntimeException(e); // Handle SQL exceptions
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle potential closing exceptions
            }
        }
    }


    public void empSelectResult(List<EmpVO>list){
        System.out.println("---------------------------------------");
        System.out.println("             사원 정보");
        System.out.println("---------------------------------------");

        for(EmpVO e : list){
            System.out.print(e.getEmpNO() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal()+" ");
            System.out.print(e.getComm() + " ");
            System.out.print(e.getDeptNo());
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }
}

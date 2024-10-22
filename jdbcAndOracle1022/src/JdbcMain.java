import dao.EmpDAO;
import vo.EmpVO;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect(){
        Scanner sc = new Scanner(System.in);
        EmpDAO dao = new EmpDAO();
        //List<EmpVO> list = dao.empSelect();
        //dao.empSelectResult(list);
        while (true) {
            System.out.println("===== EMP TABLE =====");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]select [2]insert [3]update [4]delete [5]exit : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1 :
                    List<EmpVO> list = dao.empSelect();
                    dao.empSelectResult(list);
                    break;
                case 2 :
                    boolean isSuccess = dao.empInsert(empInput());
                    if(isSuccess) System.out.println("사원 등록에 성공했습니다.");
                    else System.out.println("사원 등록에 실패했습니다.");
                    break;
                case 3 :
                    boolean isSuccess1 = dao.empUpdate();
                    if (isSuccess1) System.out.println("사원 수정에 성공했습니다.");
                    else System.out.println("사원 수정에 실패했습니다.");
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    public static EmpVO empInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("사원정보를 입력하세요");
        System.out.println("사원번호 : ");
        int empNo = sc.nextInt();
        System.out.println("사원 이름 : ");
        String name = sc.next();
        System.out.println("직책 : ");
        String job = sc.next();
        System.out.println("상관 사원번호 : ");
        int mgr = sc.nextInt();
        System.out.println("입사일 : ");
        String date = sc.next(); // 문자열로 입력 받아도 날짜 형식에 맞으면 입력 가능
        System.out.println("급여 : ");
        BigDecimal sal = sc.nextBigDecimal();
        System.out.println("성과급 : ");
        BigDecimal comm = sc.nextBigDecimal();
        System.out.println("부서 번호 : ");
        int deptNo = sc.nextInt();
        EmpVO vo = new EmpVO(empNo, name , job, mgr, Date.valueOf(date), sal, comm, deptNo);
        return vo;
    }



}

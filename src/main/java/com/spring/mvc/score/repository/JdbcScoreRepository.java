package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")//스프링컨테이너에 등록
@Log4j2
public class JdbcScoreRepository implements ScoreRepository{

    //JDBC(자바 API): 스프링 없이 자바에서 바로 DB연결하는 방식,
    //               데이터 인터페이스 베이스고,
    //               오라클같은 프로그램이 구현

    //DB 접속 정보 설정
    private String userId = "spring3";
    private String userPw = "1234";
    //jdbc:oracle:<drivertype>:<user>/<password>@<database>//dbUrl 작성순서
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";//oracle jdbc url 검색 후 공식문서 들어가보기
    private String driver = "oracle.jdbc.driver.OracleDriver";//db연결정보를 생성해주는 클래스

    @Override
    public void save(Score score) {


        String sql ="INSERT INTO score VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";//값이 변동되는 부분은 물음표 처리
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl,userId,userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 함
            //parameterindex 는 ?의 순서대로 작성
            pstmt.setString(1, score.getName());//물음표 채우고
            pstmt.setInt(2, score.getKor());//물음표 채우고
            pstmt.setInt(3, score.getEng());//물음표 채우고
            pstmt.setInt(4, score.getMath());//물음표 채우고
            pstmt.setInt(5, score.getTotal());//물음표 채우고
            pstmt.setDouble(6, score.getAverage());//물음표 채우고

            //INSERT, UPDATE, DELETE 문은 executeUpdate() 메서드 사용
            int resultNum = pstmt.executeUpdate();//리턴값 존재, 행의 수
            if(resultNum == 0) {
             log.info("데이터 삽입 실패!");
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            //5. DB자원 해제
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();//db에 보낼 때 리스트 필요
            Connection conn = null;// finally에서 닫을 때 conn이 필요하므로 전역으로 빼줌

        try {
            //1. 드라이버 로딩
            Class.forName(driver);

            //2. 연결정보 객체 생성
            conn = DriverManager.getConnection(dbUrl, userId, userPw);

            //3. sql실행 객체 생성
            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);//SQL문장을 넣는 메서드..

            //4.sql실행
            ResultSet rs = pstmt.executeQuery();//SELECT는 리턴이 있으므로 리턴값을 받아줌
//            rs.next();//커서를 한 행씩 밑으로 이동하는.....한번 입력하면 첫번째 행으로 이동

            while(rs.next()) {//커서를 옮기는 동안 반복
                scoreList.add(new Score(rs));//컨트롤 알트 l 리팩터링//스코어객체를 추가
            }


        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            //5. DB자원 해제
            try {
                if(conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {

        String sql ="SELECT * FROM score WHERE stu_num=?";//값이 변동되는 부분은 물음표 처리
        Connection conn = null;
        try {
         Class.forName(driver);
         conn = DriverManager.getConnection(dbUrl,userId,userPw);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 함
            //parameterindex 는 ?의 순서대로 작성, 한개니까 1로 씀
            pstmt.setInt(1, stuNum);//물음표 채우고
            ResultSet rs = pstmt.executeQuery();//실행

            if(rs.next()) {
                return new Score(rs);
            }

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            //5. DB자원 해제
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void remove(int stuNum) {

        String sql ="DELETE FROM score WHERE stu_num=?";//값이 변동되는 부분은 물음표 처리
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbUrl,userId,userPw);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //?를 채워야 함
            //parameterindex 는 ?의 순서대로 작성, 한개니까 1로 씀
            pstmt.setInt(1, stuNum);//물음표 채우고
            ResultSet rs = pstmt.executeQuery();//실행

        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            //5. DB자원 해제
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}

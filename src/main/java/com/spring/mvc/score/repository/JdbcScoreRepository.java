package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")//스프링컨테이너에 등록
@Log4j2
public class JdbcScoreRepository implements ScoreRepository{

    //DB 접속 정보 설정
    private String userId = "spring3";
    private String userPw = "1234";
    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";//db연결정보를 생성해주는 클래스

    @Override
    public void save(Score score) {

    }

    @Override
    public List<Score> findAll() {
        List<Score> scoreList = new ArrayList<>();//db에 보낼 때 리스트 필요

        try {
            //1. 드라이버 로딩
            Class.forName(driver);

            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(dbUrl, userId, userPw);

            //3. sql실행 객체 생성
            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4.sql실행
            ResultSet rs = pstmt.executeQuery();
//            rs.next();//커서를 한 행씩 밑으로 이동하는.....한번하면 첫번째 행으로 이동

            while(rs.next()) scoreList.add(new Score(rs));//컨트롤 알트 l 리팩터링

        }catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {
        return null;
    }

    @Override
    public void remove(int stuNum) {

    }
}

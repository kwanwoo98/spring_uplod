package ogrg.zerock.webtest1.dao;

import lombok.Cleanup;
import ogrg.zerock.webtest1.domain.MemberVO;
import ogrg.zerock.webtest1.dto.MemberDTO;
import ogrg.zerock.webtest1.util.DBUtil;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberDAO {
    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());

    public void insertMember(MemberDTO memberDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "INSERT INTO member (member_id, member_pw, name, phone, email1, email2, gender, agree, create_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDTO.getMemberId());
            pstmt.setString(2, memberDTO.getMemberPw());
            pstmt.setString(3, memberDTO.getName());
            pstmt.setString(4, memberDTO.getPhone());
            pstmt.setString(5, memberDTO.getEmail1());
            pstmt.setString(6, memberDTO.getEmail2());
            pstmt.setString(7, memberDTO.getGender());
            pstmt.setBoolean(8, memberDTO.isAgree());
            pstmt.setDate(9, Date.valueOf(memberDTO.getCreateDate()));

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.log(Level.INFO, "데이터베이스에 회원 정보가 성공적으로 추가되었습니다.");
            } else {
                LOGGER.log(Level.WARNING, "데이터베이스에 회원 정보를 추가하는 데 실패했습니다.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.", e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "데이터베이스 연결 해제 중 오류가 발생했습니다.", ex);
            }
        }
    }

    public MemberVO getWithPassword(String member_id, String member_pw) throws Exception {
        String query = "SELECT member_id, member_pw, name FROM member WHERE member_id=? AND member_pw=?";
        MemberVO memberVO = new MemberVO();
        @Cleanup Connection conn = DBUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, member_id);
        preparedStatement.setString(2, member_pw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        memberVO = MemberVO.builder()
                .mid(resultSet.getString("id"))
                .mpw(resultSet.getString("pw"))
                .mname(resultSet.getString("name"))
                .build();
        return memberVO;
    }
}

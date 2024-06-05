package ogrg.zerock.webtest1.dao;

import lombok.extern.log4j.Log4j2;
import ogrg.zerock.webtest1.dto.NoticeDTO;
import ogrg.zerock.webtest1.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class NoticeDAO {
    public void insertNotice(NoticeDTO noticeDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "INSERT INTO notice (title, content) " +
                    "VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, noticeDTO.getTitle());
            pstmt.setString(2, noticeDTO.getContent());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                log.info("데이터베이스에 공지 정보가 성공적으로 추가되었습니다.");
            } else {
                log.info("데이터베이스에 공지 정보를 추가하는 데 실패했습니다.");
            }
        } catch (SQLException e) {
            log.info("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.", e);
        } finally {
            closeResources(conn, pstmt);
        }
    }

    public List<NoticeDTO> getNoticeList() {
        List<NoticeDTO> noticeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT no, title, content, create_date FROM notice";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                NoticeDTO noticeDTO = new NoticeDTO();
                noticeDTO.setNo(rs.getInt("no"));
                noticeDTO.setTitle(rs.getString("title"));
                noticeDTO.setContent(rs.getString("content"));
                noticeDTO.setCreate_date(rs.getDate("create_date").toLocalDate());

                noticeList.add(noticeDTO);
            }
        } catch (SQLException e) {
            log.info("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.", e);
        } finally {
            closeResources(conn, pstmt, rs);
        }

        return noticeList;
    }

    public NoticeDTO getNoticeByNo(int noticeNo) {
        NoticeDTO noticeDTO = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT title, content, create_date, count FROM notice WHERE no = ?");
        ) {
            pstmt.setInt(1, noticeNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    noticeDTO = new NoticeDTO();
                    noticeDTO.setNo(noticeNo);
                    noticeDTO.setTitle(rs.getString("title"));
                    noticeDTO.setContent(rs.getString("content"));
                    noticeDTO.setCreate_date(rs.getDate("create_date").toLocalDate());
                    noticeDTO.setCount(rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            log.error("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.", e);
        }

        return noticeDTO;
    }

    public void deleteNotice(int noticeNo) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM notice WHERE no = ?");
        ) {
            pstmt.setInt(1, noticeNo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.", e);
        }
    }

    private void closeResources(Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            log.info("데이터베이스 연결 해제 중 오류가 발생했습니다.", ex);
        }
    }

    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        closeResources(conn, pstmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            log.info("데이터베이스 결과 집합 해제 중 오류가 발생했습니다.", ex);
        }
    }
}

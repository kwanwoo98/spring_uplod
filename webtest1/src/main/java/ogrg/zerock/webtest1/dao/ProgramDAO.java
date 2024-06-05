package ogrg.zerock.webtest1.dao;

import ogrg.zerock.webtest1.dto.ProgramDTO;
import ogrg.zerock.webtest1.util.DBUtil;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAO {
    public List<ProgramDTO> getAllPrograms() {
        List<ProgramDTO> programList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "SELECT no, title, text, subrtext, schedule, img, create_date FROM program";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProgramDTO program = new ProgramDTO();
                program.setNo(rs.getInt("no"));
                program.setTitle(rs.getString("title"));
                program.setText(rs.getString("text"));
                program.setSubrText(rs.getString("subrtext"));
                program.setSchedule(rs.getString("schedule"));
                program.setImg(rs.getString("img"));
                program.setCreate_date(rs.getDate("create_date").toLocalDate());
                programList.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 자원 해제
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return programList;
    }
}

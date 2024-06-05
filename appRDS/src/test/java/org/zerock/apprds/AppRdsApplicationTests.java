package org.zerock.apprds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootTest
class AppRdsApplicationTests {
  @Autowired
  private DataSource dataSource;

  @Test
  void contextLoads() {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT now()");
         ResultSet rs = pstmt.executeQuery();) {
      rs.next();
      System.out.println("현재 시간 : "+rs.getString(1));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}

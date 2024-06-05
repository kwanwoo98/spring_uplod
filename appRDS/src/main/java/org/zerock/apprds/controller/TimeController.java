package org.zerock.apprds.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@RestController
@RequestMapping("/api/time")
@Log4j2
@RequiredArgsConstructor
public class TimeController {
  private final DataSource dataSource;
  @GetMapping("/now")
  public Map<String,String> getNow() {
    String now = "";
    try (Connection conn = dataSource.getConnection();
         PreparedStatement ps = conn.prepareStatement("select now()");
         ResultSet rs = ps.executeQuery();) {
      rs.next();
      now = rs.getString(1);
      log.info("NOW : " + now);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Map.of("NOW", now);
  }
}

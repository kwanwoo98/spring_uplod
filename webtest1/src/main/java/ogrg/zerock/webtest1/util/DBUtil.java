package ogrg.zerock.webtest1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBUtil {
    INSTANCE;

    private static final String DB_URL = "jdbc:mariadb://localhost:3307/touristdb"; // 수정
    private static final String DB_USER = "webuser";
    private static final String DB_PASSWORD = "webuser";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("DB 드라이버를 찾을 수 없습니다.");
        }
    }
}

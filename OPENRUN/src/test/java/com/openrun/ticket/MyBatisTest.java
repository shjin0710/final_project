package com.openrun.ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class MyBatisTest {
@Test
public void test() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver"); // 마리아DB
    // Class.forName("com.mysql.jdbc.Driver"); MySQL

    Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test", "root", "1234");// 마리아DB
    // Connection con =     DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","passwd"); MySQL
    System.out.println(con);
    }
}
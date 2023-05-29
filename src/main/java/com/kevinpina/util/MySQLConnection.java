package com.kevinpina.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySQLConnection {

    private static final String url = "jdbc:mysql://localhost:3306/enterprise?serverTimezone=America/Santiago";// ?serverTimezone=America/Santiago
    private static final String username = "root";
    private static final String password = "249861";
    private static final int INITIAL_CONNECTION = 3; // By default 0
    private static final int MINIMAL_INACTIVE_CONNECTION = 3; // By default 0
    private static final int MAXIMUM_INACTIVE_CONNECTION = 8; // By default 8
    private static final int MAXIMUM_ACTIVE_INACTIVE_CONNECTION = 8; // By default 8
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(password);
            pool.setInitialSize(INITIAL_CONNECTION);
            pool.setMinIdle(MINIMAL_INACTIVE_CONNECTION);
            pool.setMaxIdle(MAXIMUM_INACTIVE_CONNECTION);
            pool.setMaxTotal(MAXIMUM_ACTIVE_INACTIVE_CONNECTION);
        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}

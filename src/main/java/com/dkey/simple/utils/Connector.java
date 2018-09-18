package com.dkey.simple.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Connector {
    private static Context context = null;
    private static DataSource dataSource = null;

    public static DataSource getDatasource() {
        try {
            if(context == null)
                context = new InitialContext();
            dataSource = (DataSource) context.lookup("mysqlDS");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return dataSource;
    }
}

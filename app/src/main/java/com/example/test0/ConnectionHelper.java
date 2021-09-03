package com.example.test0;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    private static Connection connect;

    private static Connection Connectionhelper(){
        String username="EBook";
        String password="BuibinhBKSG0123";
        String database="EBook";
        String ip="192.168.1.6";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = "";
        try{
            Log.d("TryConnection","Try Connected");
            connectionURL= "jdbc:jtds:sqlserver://"+ ip + ";instance=MSSQLSERVER;user="+username+";password="+password+";databasename="+database+";";
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL);
            Log.e("ASK", "Connection called");
        }
        catch (Exception e){
            Log.e("ASK", e.getMessage());
        }
        return connection;
    }

    public static Connection getConnect() {
        if(connect==null){
            Log.d("testConnectionIf","Connected");
            connect = Connectionhelper();
            Log.d("testConnection","Connected");
        }
        Log.d("testReturnConnection","Return Connected");
        return connect;
    }
}

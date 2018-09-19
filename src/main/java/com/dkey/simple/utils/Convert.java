package com.dkey.simple.utils;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

public class Convert {

    public static JSONArray toJSONArray (ResultSet rs){
        JSONArray json = new JSONArray();
        try{
            ResultSetMetaData rsm = rs.getMetaData();
            while(rs.next()){
                int numColumns = rsm.getColumnCount();
                JSONObject object = new JSONObject();
                for(int i=1; i<numColumns; i++){
                    String columnName = rsm.getColumnName(i);
                    if(rsm.getColumnType(i) == Types.ARRAY){
                        object.put(columnName, rs.getArray(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.BIGINT){
                        object.put(columnName, rs.getInt(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.BOOLEAN){
                        object.put(columnName, rs.getBoolean(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.BLOB){
                        object.put(columnName, rs.getBlob(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.DOUBLE){
                        object.put(columnName, rs.getDouble(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.FLOAT){
                        object.put(columnName, rs.getFloat(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.NVARCHAR){
                        object.put(columnName, rs.getNString(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.VARCHAR){
                        object.put(columnName, rs.getString(columnName)!=null?rs.getString(columnName):null);
                    }
                    else if(rsm.getColumnType(i) == Types.TINYINT){
                        object.put(columnName, rs.getInt(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.SMALLINT){
                        object.put(columnName, rs.getInt(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.DATE){
                        object.put(columnName, rs.getDate(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.TIMESTAMP){
                        object.put(columnName, rs.getTimestamp(columnName));
                    }
                    else if(rsm.getColumnType(i) == Types.NUMERIC){
                        object.put(columnName, rs.getBigDecimal(columnName));
                    }
                    else{
                        object.put(columnName,rs.getObject(columnName));
                    }
                }
                json.put(object);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

        }
        return json;
    }
}

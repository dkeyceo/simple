package com.dkey.simple.controller;

import com.dkey.simple.utils.Connector;
import com.dkey.simple.utils.Convert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("controller")
public class SimpleController {

    @GET
    @Path("info")
    @Produces(MediaType.TEXT_HTML)
    public String getInfo(){
        return "<h1> Hello from resource! </h1>";
    }

    @GET
    @Path("rows")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCountRows () throws Exception{

        PreparedStatement ps = null;
        Connection conn = null;
        String query = "select count(*) cnt from cars";
        ResultSet rs = null;
        int rowCount = 0;
        try{
            conn = Connector.getDatasource().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                rowCount = rs.getInt("cnt");
            }
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) conn.close();
        }
        return rowCount;
    }
    @GET
    @Path("cars")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCars () throws Exception{

        PreparedStatement ps = null;
        Connection conn = null;
        String query = "select * from cars where birthday < '1910-01-01'";
        ResultSet rs = null;
        String resultJson = "";
        try{
            conn = Connector.getDatasource().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            resultJson = Convert.toJSONArray(rs).toString();
            ps.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) conn.close();
        }
        return resultJson;
    }

}

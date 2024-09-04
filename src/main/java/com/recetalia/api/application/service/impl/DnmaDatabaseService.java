package com.recetalia.api.application.service.impl;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DnmaDatabaseService {

    private static final String DNMA_DB_URL = "jdbc:mysql://143.110.212.167:3306/recetali_dnma";
    private static final String DNMA_DB_USER = "recetali_receuser";
    private static final String DNMA_DB_PASSWORD = "RecePass2021";

    public Map<String, Map<String, String>> fetchAmpDetails(List<String> ampIds) {
        Map<String, Map<String, String>> ampDetailsMap = new HashMap<>();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DNMA_DB_URL, DNMA_DB_USER, DNMA_DB_PASSWORD);
            Statement statement = connection.createStatement();
            String ampIdList = ampIds.stream().map(id -> "'" + id + "'").collect(Collectors.joining(","));
            String query = "SELECT AMP_Id, AMP_DSC as amp_dsc, PROD_MSP as prod_msp, l.NOMBRE as nombre_laboratory, l.RUT as rut_laboratory " +
                    "FROM amp " +
                    "INNER JOIN laboratorio as l ON l.LAB_Id = LABORATORIO_Id " +
                    "WHERE AMP_Id IN (" + ampIdList + ")";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                try {
                    Map<String, String> ampDetails = new HashMap<>();
                    ampDetails.put("amp_dsc", resultSet.getString("amp_dsc"));
                    ampDetails.put("prod_msp", resultSet.getString("prod_msp"));
                    ampDetails.put("nombre_laboratory", resultSet.getString("nombre_laboratory"));
                    ampDetails.put("rut_laboratory", resultSet.getString("rut_laboratory"));
                    ampDetailsMap.put(resultSet.getString("AMP_Id"), ampDetails);
                } catch (Exception e) {
                    System.err.println("DnmaDatabaseService: " + e.getMessage());
                }
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ampDetailsMap;
    }
}

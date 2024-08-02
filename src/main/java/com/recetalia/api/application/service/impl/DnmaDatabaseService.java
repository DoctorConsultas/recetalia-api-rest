package com.recetalia.api.application.service.impl;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Service
public class DnmaDatabaseService {

    private static final String DNMA_DB_URL = "jdbc:mysql://143.110.212.167:3306/recetali_dnma";
    private static final String DNMA_DB_USER = "recetali_receuser";
    private static final String DNMA_DB_PASSWORD = "RecePass2021";

    public Map<String, String> fetchAmpDetails(String ampId) {
        Map<String, String> ampDetails = new HashMap<>();
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DNMA_DB_URL, DNMA_DB_USER, DNMA_DB_PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT AMP_DSC as amp_dsc, PROD_MSP as prod_msp, l.NOMBRE as nombre_laboratory, l.RUT as rut_laboratory " +
                    "FROM amp " +
                    "INNER JOIN laboratorio as l ON l.LAB_Id = LABORATORIO_Id " +
                    "WHERE AMP_Id = " + ampId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                ampDetails.put("amp_dsc", resultSet.getString("amp_dsc"));
                ampDetails.put("prod_msp", resultSet.getString("prod_msp"));
                ampDetails.put("nombre_laboratory", resultSet.getString("nombre_laboratory"));
                ampDetails.put("rut_laboratory", resultSet.getString("rut_laboratory"));
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
        return ampDetails;
    }
}
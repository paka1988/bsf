package com.bsf.database.mysql;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.bsf.database.DbInterface;

/**
 * Database implementation for bsf database.
 *
 * @author pkalashnikov
 *
 */
public final class BsfDB implements DbInterface {

    private static BsfDB instance = null;
    private static final String DB_NAME = "bsfdb";
    private static final String DB_PORT = "3306";
    private String dbHost;
    private String dbUser;
    private String dbPassword;
    private Properties properties;

    /**
     * Private constructor in order to use the Singleton pattern.
     */
    private BsfDB() {

        try {

            readCredentials();
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception e) {

            throw new RuntimeException("Problem to load 'com.mysql.jdbc.Driver':", e);
        }
    }

    /**
     *
     * @return instance of db object.
     */
    public static BsfDB getInstance() {

        if (instance == null) {

            instance = new BsfDB();
        }

        return instance;
    }

    /**
     * Connects to the mysql database.
     */
    @Override
    public Connection getConnection() {

        Connection conn = null;

        // build connection path
        final String connPath = String.format("jdbc:mysql://%s:%s/%s", dbHost, DB_PORT, DB_NAME);

        try {

            conn = DriverManager.getConnection(connPath, dbUser, dbPassword);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return conn;
    }

    /**
     * Reads credentials from the property file.
     */
    private void readCredentials() {

        properties = new Properties();

        try {

            final String credentialPath = String.format("/%s/BSF/%s", System.getProperty("user.home"), "bsf.properties");

            InputStream input = new FileInputStream(credentialPath);
            properties.load(input);

            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");
            dbHost = properties.getProperty("dbHost");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

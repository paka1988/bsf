package com.bsf.database;

import java.sql.Connection;

/**
 * Interface for database.
 *
 * @author pkalashnikov
 *
 */
public interface DbInterface {

    /**
     * Get a database connection.
     *
     * @return {@link Connection}
     */
    Connection getConnection();
}

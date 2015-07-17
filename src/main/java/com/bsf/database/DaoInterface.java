package com.bsf.database;

import com.bsf.model.User;

/**
 * DAO interface.
 *
 * @author pkalashnikov
 *
 */
public interface DaoInterface {

    /**
     *
     * @param email
     *            user email
     * @return user as object.
     */
    User getUser(final String email);
}

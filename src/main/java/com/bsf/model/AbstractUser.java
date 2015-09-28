package com.bsf.model;

import com.bsf.database.mysql.DataAccessObject;
import com.bsf.i18n.LanguageCode;

/**
 * Generic user.
 *
 * @author pkalashnikov
 *
 */
public abstract class AbstractUser {

    /**
     * instance to access data base objects.
     */
    protected static final DataAccessObject DAO = new DataAccessObject();

    /**
     *
     * @return language code.
     */
    public abstract LanguageCode getLanguageCode();
}

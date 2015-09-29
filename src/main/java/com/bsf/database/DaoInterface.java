package com.bsf.database;

import com.bsf.model.AnonymousUser;
import com.bsf.model.System;
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
     * @param user anonymous user.
     */
    void deleteAnonymousUser(final AnonymousUser user);

    /**
     *
     * @param user user.
     */
    void deleteUser(final User user);

    /**
     *
     * @param sessionId unique session id.
     * @return anonymous user as object.
     */
    AnonymousUser getAnonymousUser(final String sessionId);

    /**
     *
     * @return system as java object.
     */
    System getSystem();

    /**
     *
     * @param email
     *            user email
     * @return user as object.
     */
    User getUser(final String email);

    /**
     *
     * @param user to be inserted.
     */
    void setAnonymousUser(final AnonymousUser user);

    /**
     *
     * @param user user.
     */
    void setUser(final User user);

    /**
     *
     * @param system object
     */
    void updateSystem(final System system);
}

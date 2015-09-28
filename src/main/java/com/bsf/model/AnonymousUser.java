package com.bsf.model;

import java.util.Date;

import com.bsf.i18n.LanguageCode;

/**
 * Instance of this class corresponds to entry in table AnonymousUsers.
 *
 * @author pkalashnikov
 *
 */
public class AnonymousUser extends AbstractUser {

    private String sessionId;
    private LanguageCode languageCode;
    private long created;
    private long validUntil;

    /**
     *
     * @param sessionId unique session id.
     * @param lc language code.
     */
    public AnonymousUser(final String sessionId, final LanguageCode lc) {

        this.sessionId = sessionId;
        this.languageCode = lc;
        this.created = new Date().getTime();
        this.validUntil = created + DAO.getSystem().getSessionTimeout();
    }

    /**
     *
     * @return session id.
     */
    public final String getSessionId() {
        return sessionId;
    }

    /**
     *
     * @return language code.
     */
    @Override
    public final LanguageCode getLanguageCode() {
        return languageCode;
    }

    /**
     *
     * @return time in seconds since 1970 which shows when was user created.
     */
    public final long getCreated() {

        return created;
    }

    /**
     *
     * @return time in seconds since 1970 which shows until when user is still valid.
     */
    public final long getValidUntil() {

        return validUntil;
    }
}

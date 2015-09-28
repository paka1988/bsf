package com.bsf.model;

/**
 * Represents system table in bsf data base.
 *
 * @author pkalashnikov
 *
 */
public class System {

    private long sessionTimeout;

    /**
     *
     * @return session timeout in seconds.
     */
    public final long getSessionTimeout() {

        return this.sessionTimeout;
    }

    /**
     *
     * @param seconds the time within session is valid.
     */
    public final void setSessionTimeout(final long seconds) {

        this.sessionTimeout = seconds;
    }
}

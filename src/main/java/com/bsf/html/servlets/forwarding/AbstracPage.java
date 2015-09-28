package com.bsf.html.servlets.forwarding;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsf.database.mysql.DataAccessObject;

/**
 * Abstract servlet page.
 *
 * @author pkalashnikov
 *
 */
public abstract class AbstracPage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Object to access data base information.
     */
    protected static final DataAccessObject DAO = new DataAccessObject();

    /**
     * Specifies the number of seconds until servlet container will invalidate the session.
     */
    private String sessionTimeout = "3600";

    /**
     *
     * @return servlet request.
     */
    public abstract HttpServletRequest getServletRequest();

    /**
     *
     * @return servlet response.
     */
    public abstract HttpServletResponse getServletResponse();

    /**
     *
     * @return session timeout in seconds.
     */
    public final int getSessionTimeout() {

        return Integer.valueOf(sessionTimeout);
    }

    /**
     *
     * @param timeout timeout in seconds.
     */
    public final void setSessionTimeout(final int timeout) {

        this.sessionTimeout = String.valueOf(timeout);
    }
}

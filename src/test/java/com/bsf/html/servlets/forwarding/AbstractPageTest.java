package com.bsf.html.servlets.forwarding;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractPageTest {

    @Test
    public final void getSessionTimeout() {

        final int defaultSession = 3600;

        AbstracPage ap = new CompanyPage();

        assertEquals(defaultSession, ap.getSessionTimeout());
    }

    @Test
    public final void setSessionTimeout() {

        final int newSession = 10;

        AbstracPage ap = new CompanyPage();

        ap.setSessionTimeout(newSession);
        assertEquals(newSession, ap.getSessionTimeout());
    }
}

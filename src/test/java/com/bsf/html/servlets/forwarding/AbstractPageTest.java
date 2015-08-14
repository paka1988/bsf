package com.bsf.html.servlets.forwarding;

import static org.mockito.Mockito.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bsf.i18n.I18n;
import com.bsf.i18n.LanguageCode;

import static org.junit.Assert.*;

public class AbstractPageTest {

    private static HttpServletRequest req;
    private static HttpServletResponse resp;

    @BeforeClass
    public final static void initData() {

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
    }

    @Test
    public final void setLanguageTestLanguageEn() {

        AbstracPage ap = new AbstracPage();

        when(req.getParameter("language")).thenReturn("en_GB");
        when(req.getCookies()).thenReturn(new Cookie[]{new Cookie("language", "de_DE")});
        ap.setLanguage(req, resp);

        assertEquals(I18n.getLanguage(), LanguageCode.en_GB);
    }

    @Test
    public final void setLanguageTestLanguageDe() {

        AbstracPage ap = new AbstracPage();

        when(req.getParameter("language")).thenReturn("de_DE");
        when(req.getCookies()).thenReturn(new Cookie[]{new Cookie("language", "en_GB")});
        ap.setLanguage(req, resp);

        assertEquals(I18n.getLanguage(), LanguageCode.de_DE);
    }
}

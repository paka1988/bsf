package com.bsf.html.servlets.forwarding;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyPageTest {

    private static CompanyPage servletCp;

    private static HttpServletRequest req;
    private static HttpServletResponse resp;
    private static HttpSession sess;
    private static ServletContext sc;
    private static PrintWriter pw;


    private static String testHtmlMainMenu = "WebContent/app/templates/main-menu.html";
    private static String testHtmlIndex = "WebContent/index.html";
    private static String actualHtml = "src/test/resources/companyPageVar.html";
    private static String expectedHtml = "src/test/resources/companyPageStatic_en.html";
    private static String dict_en = "src/main/resources/dictionary/Dictionary.en.json";

    @BeforeClass
    public static final void initData() throws Exception {

        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        sess = mock(HttpSession.class);
        sc = mock(ServletContext.class);

        when(req.getSession(true)).thenReturn(sess);
        when(req.getServerName()).thenReturn("localhost");
        when(req.getServerPort()).thenReturn(8080);
        when(req.getServletContext()).thenReturn(sc);
        when(sc.getRealPath("/index.html")).thenReturn(new File(testHtmlIndex).getAbsolutePath());
        when(sc.getRealPath("/app/templates/main-menu.html")).thenReturn(new File(testHtmlMainMenu).getAbsolutePath());
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.en.json")).thenReturn(new File(dict_en).getAbsolutePath());

        pw = new PrintWriter(new File(actualHtml));

        when(resp.getWriter()).thenReturn(pw);

        servletCp = new CompanyPage();
    }

    @Test
    public final void companyPageDoGetTest() throws Exception {

        servletCp.doGet(req, resp);
        pw.flush();

        final String expected = Jsoup.parse(new File(expectedHtml),
                StandardCharsets.UTF_8.name()).html().replaceAll("(?<=>)[ ]+", "");

        final String actual = Jsoup.parse(new File(actualHtml),
                StandardCharsets.UTF_8.name()).html().replaceAll("(?<=>)[ ]+", "");

        assertEquals(expected, actual);
    }

    @AfterClass
    public static final void cleanUp() throws IOException {

        pw.close();
        FileUtils.forceDelete(new File(actualHtml));
    }
}

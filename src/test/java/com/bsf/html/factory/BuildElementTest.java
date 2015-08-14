package com.bsf.html.factory;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.BeforeClass;
import org.junit.Test;

public class BuildElementTest {

    private static BuildElement be;
    private static String testHtml = "WebContent/app/templates/main-menu.html";
    private static String dict_en = "src/main/resources/dictionary/Dictionary.en.json";

    @BeforeClass
    public static final void initData() {

        HttpServletRequest req = mock(HttpServletRequest.class);
        ServletContext sc = mock(ServletContext.class);

        when(req.getServerName()).thenReturn("localhost");
        when(req.getServerPort()).thenReturn(8080);
        when(req.getServletContext()).thenReturn(sc);
        when(sc.getRealPath("/app/templates/main-menu.html")).thenReturn(new File(testHtml).getAbsolutePath());
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.en.json")).thenReturn(new File(dict_en).getAbsolutePath());

        be = new BuildElement(req);
    }

    @Test
    public final void createCssLinkTest() {

        final Element expected = Jsoup.parse("<link rel='stylesheet' type='text/css' href='localhost:8080/test'>",
                StandardCharsets.UTF_8.name());

        assertEquals(expected.html(), be.createCssLink("localhost:8080/test").html());
    }

    @Test
    public final void createCssLinkMainTest() {

        final String href = String.format("http://%s/bsf/app/styles/main.css", "localhost:8080");
        final Element expected = Jsoup.parse("<link rel='stylesheet' type='text/css' href='" + href + "'>",
                StandardCharsets.UTF_8.name());

        assertEquals(expected.html(), be.createCssLinkMain().html());
    }

    @Test
    public final void createCssLinkPureMinTest() {

        final String href = String.format("http://%s/bsf/app/styles/pure/0.6.0/pure-min.css", "localhost:8080");
        final Element expected = Jsoup.parse("<link rel='stylesheet' type='text/css' href='" + href + "'>",
                StandardCharsets.UTF_8.name());

        assertEquals(expected.html(), be.createCssLinkPureMin().html());
    }

    @Test
    public final void createMainMenuTest() throws IOException {

        File f = new File("src/test/resources/main-menu.en.html");

        assertEquals(Jsoup.parse(f, StandardCharsets.UTF_8.name()).html(), be.createMainMenu().html());
    }
}

package com.bsf.i18n;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class I18nTest {

    private static String testHtmlEn = "src/main/resources/dictionary/Dictionary.en.json";
    private static String testHtmlDe = "src/main/resources/dictionary/Dictionary.de.json";
    private static I18n i18n = new I18n(LanguageCode.en_GB);
    private static ServletContext sc;

    @BeforeClass
    public static final void initData() {

        HttpServletRequest req = mock(HttpServletRequest.class);
        sc = mock(ServletContext.class);

        when(req.getServerName()).thenReturn("localhost");
        when(req.getServerPort()).thenReturn(8080);
        when(req.getServletContext()).thenReturn(sc);
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.en.json")).thenReturn(new File(testHtmlEn).getAbsolutePath());
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.de.json")).thenReturn(new File(testHtmlDe).getAbsolutePath());

        i18n.setServletContext(sc);
    }

    @Test
    public final void getDictionaryTest() throws IOException {

        JSONObject jsobjEn = i18n.getDictionary();

        // check english
        Assert.assertEquals("About", jsobjEn.get("About"));

        i18n = new I18n(LanguageCode.de_DE);
        i18n.setServletContext(sc);
        JSONObject jsobjDe = i18n.getDictionary();

        // check german
        Assert.assertEquals("Hilfe", jsobjDe.get("About"));
    }
}

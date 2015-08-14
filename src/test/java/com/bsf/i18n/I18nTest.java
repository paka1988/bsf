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

    @BeforeClass
    public static final void initData() {

        HttpServletRequest req = mock(HttpServletRequest.class);
        ServletContext sc = mock(ServletContext.class);

        when(req.getServerName()).thenReturn("localhost");
        when(req.getServerPort()).thenReturn(8080);
        when(req.getServletContext()).thenReturn(sc);
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.en.json")).thenReturn(new File(testHtmlEn).getAbsolutePath());
        when(sc.getRealPath("/WEB-INF/classes/dictionary/Dictionary.de.json")).thenReturn(new File(testHtmlDe).getAbsolutePath());

        I18n.setServletContext(sc);
    }

    @Test
    public final void getDictionaryTest() throws IOException {

        JSONObject jsobjEn = I18n.getDictionary();

        Assert.assertEquals("About", jsobjEn.get("About"));

        I18n.setLanguage(LanguageCode.de_DE);
        JSONObject jsobjDe = I18n.getDictionary();

        Assert.assertEquals("Hilfe", jsobjDe.get("About"));
    }
}

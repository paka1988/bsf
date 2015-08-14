package com.bsf.i18n;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

/**
 * Manages translations.
 *
 * @author p.kalashnikov
 *
 */
public final class I18n {

    private static LanguageCode language = LanguageCode.en_GB;
    private static ServletContext servletContext = null;

    private I18n() {
    }

    /**
     * reads translation file to string and returns json object.
     *
     * @return dictionary.
     * @throws IOException
     *             if translation files are missing.
     */
    public static JSONObject getDictionary() throws IOException {

        if (servletContext == null) {

            throw new RuntimeException("Use first I18n.setServletContext(final ServletContext context) to provide servlet context.");
        }

        switch (language) {

            case en_GB:

                final String en = FileUtils.readFileToString(new File(servletContext
                        .getRealPath("/WEB-INF/classes/dictionary/Dictionary.en.json")));

                return new JSONObject(en);

            case de_DE:

                final String de = FileUtils.readFileToString(new File(servletContext
                        .getRealPath("/WEB-INF/classes/dictionary/Dictionary.de.json")));

                return new JSONObject(de);

            default:
                throw new RuntimeException("Translation file missing for following language: " + language);
        }
    }

    /**
     *
     * @return current language value.
     */
    public static LanguageCode getLanguage() {

        return I18n.language;
    }

    /**
     *
     * @param language
     *            set system language.
     */
    public static void setLanguage(final LanguageCode language) {

        I18n.language = language;
    }

    /**
     *
     * @param context
     *            servlet context.
     */
    public static void setServletContext(final ServletContext context) {

        I18n.servletContext = context;
    }
}

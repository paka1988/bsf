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

    private LanguageCode language = null;
    private ServletContext servletContext = null;

    /**
     *
     * @param language language code e.g.: en_GB.
     */
    public I18n(final LanguageCode language) {

        this.language = language;
    }

    /**
     * reads translation file to string and returns json object.
     *
     * @return dictionary.
     * @throws IOException
     *             if translation files are missing.
     */
    public JSONObject getDictionary() throws IOException {

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
    public LanguageCode getLanguage() {

        return this.language;
    }

    /**
     *
     * @param context
     *            servlet context.
     */
    public void setServletContext(final ServletContext context) {

        this.servletContext = context;
    }
}

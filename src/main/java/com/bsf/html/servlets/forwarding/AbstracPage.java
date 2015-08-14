package com.bsf.html.servlets.forwarding;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsf.i18n.I18n;
import com.bsf.i18n.LanguageCode;

/**
 * Abstract servlet page.
 *
 * @author pkalashnikov
 *
 */
public class AbstracPage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param request servlet request.
     * @param response servlet response.
     */
    public final void setLanguage(final HttpServletRequest request, final HttpServletResponse response) {

        // read language param
        final String language = request.getParameter("language");
        final Cookie[] cookies = request.getCookies();

        if (language != null && cookies != null) {

            for (Cookie cookie : cookies) {

                if ("language".equals(cookie.getName())) {

                    I18n.setLanguage(LanguageCode.valueOf(language));
                    response.addCookie(new Cookie("language", I18n.getLanguage().name()));
                }
            }
        } else {

            response.addCookie(new Cookie("language", I18n.getLanguage().name()));
        }
    }
}

package com.bsf.html.servlets.forwarding;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.bsf.html.factory.ElementBuilder;
import com.bsf.i18n.LanguageCode;
import com.bsf.model.AnonymousUser;

/**
 * Servlet implementation class CompanyPage.
 */
public class CompanyPage extends AbstracPage {

    private static final long serialVersionUID = 1L;

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Default constructor.
     */
    public CompanyPage() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        this.request = request;
        this.response = response;

        // assure language
        final HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(this.getSessionTimeout());

        final String sessionId = session.getId();
        String language = request.getParameter("language");

        AnonymousUser au = DAO.getAnonymousUser(sessionId);

        if (language == null && au == null) {

            language = LanguageCode.en_GB.name();
            au = new AnonymousUser(sessionId, LanguageCode.valueOf(language));
            DAO.setAnonymousUser(au);
        } else if (language != null) {

            au = new AnonymousUser(sessionId, LanguageCode.valueOf(language));
            DAO.setAnonymousUser(au);
        }

        final ElementBuilder eb = new ElementBuilder(this, au);

        final String loc = request.getServletContext().getRealPath("/index.html");
        File f = new File(loc);

        // read html index
        Document html = Jsoup.parse(f, StandardCharsets.UTF_8.name());

        // add main.css
        html.select("head title").first().after(eb.createCssLinkMain().html());

        // add menu
        html.select("#menuLink").first().after(eb.createMainMenu().html());

        // add script
        html.select("head title").first().after(eb.createScriptRequierJs().html());

        // send response
        PrintWriter writer = response.getWriter();
        writer.println(html.html());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    @Override
    public HttpServletRequest getServletRequest() {

        return this.request;
    }

    @Override
    public HttpServletResponse getServletResponse() {

        return this.response;
    }

}

package com.bsf.html.factory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 * Class which generates html elements.
 *
 * @author pkalashnikov
 *
 */
public final class BuildElement {

    private String host;
    private ServletContext context;

    /**
     *
     * @param request
     *            servlet request
     * @param context
     *            servlet context
     */
    public BuildElement(final HttpServletRequest request) {

        host = String.format("%s:%s", request.getServerName(), request.getServerPort());
        this.context = request.getServletContext();
    }

    /**
     *
     * @param href
     *            reference to css file.
     * @return html element.
     */
    public Element createCssLink(final String href) {

        final String strLink = String.format("<link rel='stylesheet' type='text/css' href='%s'>", href);

        return Jsoup.parse(strLink, StandardCharsets.UTF_8.name());
    }

    /**
     * @return html link for bsf/app/styles/main.css import.
     */
    public Element createCssLinkMain() {

        final String href = String.format("http://%s/bsf/app/styles/main.css", host);

        return this.createCssLink(href);
    }

    /**
     *
     * @return html link for bsf/app/styles/pure/0.6.0/pure-min.css import.
     */
    public Element createCssLinkPureMin() {

        final String href = String.format("http://%s/bsf/app/styles/pure/0.6.0/pure-min.css", host);

        return this.createCssLink(href);
    }

    /**
     * This function reads main menu html template and converts it to jsoup
     * element.
     *
     * @return main menu html element.
     * @throws IOException
     *             if file could be found.
     */
    public Element createMainMenu() throws IOException {

        File file = new File(context.getRealPath("/app/templates/main-menu.html"));

        return Jsoup.parse(file, StandardCharsets.UTF_8.name());
    }
}

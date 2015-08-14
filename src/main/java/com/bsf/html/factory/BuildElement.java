package com.bsf.html.factory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.bsf.i18n.I18n;
import com.bsf.i18n.LanguageCode;

/**
 * Class which generates html elements.
 *
 * @author pkalashnikov
 *
 */
public final class BuildElement {

    private String host;
    private ServletContext context;
    private String servletName;
    private String appName;

    /**
     *
     * @param request
     *            servlet request
     */
    public BuildElement(final HttpServletRequest request) {

        host = String.format("%s:%s", request.getServerName(), request.getServerPort());
        context = request.getServletContext();
        appName = request.getContextPath();
        servletName = request.getServletPath();
        I18n.setServletContext(context);
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

        // read template
        File templateFile = new File(context.getRealPath("/app/templates/main-menu.html"));

        // create document
        Document mainMenu = Jsoup.parse(templateFile, StandardCharsets.UTF_8.name());

        // put translations
        mainMenu.getElementsContainingOwnText("Company").get(0).text(I18n.getDictionary().getString("Company"));
        mainMenu.getElementsContainingOwnText("Home").get(0).text(I18n.getDictionary().getString("Home"));
        mainMenu.getElementsContainingOwnText("About").get(0).text(I18n.getDictionary().getString("About"));
        mainMenu.getElementsContainingOwnText("Services").get(0).text(I18n.getDictionary().getString("Services"));
        mainMenu.getElementsContainingOwnText("Login").get(0).text(I18n.getDictionary().getString("Login"));
        mainMenu.getElementsContainingOwnText("Contact").get(0).text(I18n.getDictionary().getString("Contact"));
        mainMenu.getElementsContainingOwnText("More").get(0).text(I18n.getDictionary().getString("More"));

        // put language hrefs
        final String prefix = String.format("%s%s?language=", appName, servletName);

        mainMenu.getElementsContainingOwnText("English").get(0).attr("href", prefix + LanguageCode.en_GB.name());
        mainMenu.getElementsContainingOwnText("Deutsch").get(0).attr("href", prefix + LanguageCode.de_DE.name());

        return mainMenu;
    }

    /**
     *
     * @return script element for requier.js
     */
    public Element createScriptRequierJs() {

        final String href = String.format("http://%s/bsf/app/scripts/require.js", host);
        final String strLink = String.format("<script type='text/javascript' src='%s' async></script>", href);

        return Jsoup.parse(strLink, StandardCharsets.UTF_8.name());
    }
}

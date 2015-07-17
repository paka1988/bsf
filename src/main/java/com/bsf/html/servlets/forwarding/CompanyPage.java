package com.bsf.html.servlets.forwarding;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.bsf.html.factory.BuildElement;

/**
 * Servlet implementation class CompanyPage.
 */
public class CompanyPage extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        final BuildElement be = new BuildElement(request);

        final String loc = request.getServletContext().getRealPath("/index.html");
        File f = new File(loc);

        // read html index
        Document html = Jsoup.parse(f, StandardCharsets.UTF_8.name());

        // add pure-min.css
        html.select("head title").first().after(be.createCssLinkPureMin().html());

        // add main.css
        html.select("head title").first().after(be.createCssLinkMain().html());

        // add menu
        html.select("#menuLink").first().after(be.createMainMenu().html());

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

}

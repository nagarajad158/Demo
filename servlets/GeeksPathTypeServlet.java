package com.aem.geeks.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(
	    service = {Servlet.class},
	    property = {
	        "sling.servlet.paths=/bin/getpagepath",
	        "sling.servlet.methods=GET"
	    }
	)
public class GeeksPathTypeServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksPathTypeServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
        try {
            Resource resource = req.getResource();
            String pagePath = resource.getPath();
            resp.getWriter().write(pagePath);
        } catch (Exception e) {
            // Handle exceptions
        }
}
    
}
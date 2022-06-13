package com.revature.reimbursements.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.services.TokenService;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.servlets.AuthServlet;
import com.revature.reimbursements.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* Need this ContextLoaderListener for our dependency injection upon deployment. */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nInitializing Reimbursements web application");

        /* ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects) */
        ObjectMapper mapper = new ObjectMapper();

        /* Dependency injection. */
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()), new TokenService(new JwtConfig()));
        AuthServlet authServlet = new AuthServlet(mapper, new UserService(new UserDAO()), new TokenService(new JwtConfig()));

        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();

        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down Reimbursements web application");
    }
}
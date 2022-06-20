package com.revature.reimbursements.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.daos.ReimbursementDAO;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.daos.UserRoleDAO;
import com.revature.reimbursements.services.*;
import com.revature.reimbursements.servlets.AuthServlet;
import com.revature.reimbursements.servlets.ReimbursementServlet;
import com.revature.reimbursements.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* Need this ContextLoaderListener for our dependency injection upon deployment. */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nInitializing Reimbursements web application");




        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
        /* ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects) */
        ObjectMapper mapper = new ObjectMapper();

        /* Dependency injection. */
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()), new UserRoleService(new UserRoleDAO()), new TokenService(new JwtConfig()), new AdminService(new UserDAO()));
        AuthServlet authServlet = new AuthServlet(mapper, new UserService(new UserDAO()), new AdminService(new UserDAO()), new TokenService(new JwtConfig()));
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new ReimbursementService(new ReimbursementDAO()), new TokenService(new JwtConfig()));

        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();

        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursement/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down Reimbursements web application");
    }
}
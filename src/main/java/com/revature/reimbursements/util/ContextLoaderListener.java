package com.revature.reimbursements.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursements.daos.UserDAO;
import com.revature.reimbursements.services.UserService;
import com.revature.reimbursements.servlets.AuthServlet;
import com.revature.reimbursements.servlets.TestServlet;
import com.revature.reimbursements.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nInitializing Reimbursement web application");

        ObjectMapper mapper = new ObjectMapper();

        TestServlet servlet = new TestServlet();
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()));
        AuthServlet authServlet = new AuthServlet(mapper, new UserService(new UserDAO()));

        ServletContext context = sce.getServletContext();
        context.addServlet("Servlet", servlet).addMapping("/test");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down Reimbursement web application");
    }
}

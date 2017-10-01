package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import services.DBServiceImpl;
import services.interfaces.AccountService;
import services.AccountServiceImpl;
import services.datasets.UserProfile;
import services.interfaces.DBService;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBServiceImpl();
       // AccountService accountService = new AccountServiceImpl();
        //accountService.addUser(new UserProfile("test"));
        //accountService.addUser(new UserProfile("admin"));

       Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceHandler contentHandler = new ResourceHandler();
        contentHandler.setDirectoriesListed(false);
        contentHandler.setResourceBase("templates");
        contentHandler.setWelcomeFiles(new String[]{"index.html"});
        //contentHandler.setResourceBase(".");

        context.addServlet(new ServletHolder(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(PageGenerator.getInstance()
                        .getPage("index.html", new HashMap<>()));
            }
        }), "/");
        context.getInitParams().put("useFileMappedBuffer", "false");
        context.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
        HandlerList handlers = new HandlerList();
        handlers.addHandler(contentHandler);
        handlers.addHandler(context);

        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbService.printConnectionInfo();

    }
}

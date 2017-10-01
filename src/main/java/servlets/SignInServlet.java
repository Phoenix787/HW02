package servlets;

import services.datasets.UserDataSet;
import services.interfaces.AccountService;
import services.datasets.UserProfile;
import services.interfaces.DBService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVars = new HashMap<>();
        String sessionId = req.getSession().getId();
        UserDataSet user = dbService.getUserBySession(sessionId);
        if (user != null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            pageVars.put("message", "Авторизация успешна. Добро пожаловать, "  +  user.getName());

            resp.getWriter().println(PageGenerator.getInstance().getPage("inform.html", pageVars));
        }
        else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(PageGenerator.getInstance().getPage("authform.html", pageVars));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVars = new HashMap<>();
        resp.setContentType("text/html;charset=utf-8");
        UserDataSet user = dbService.getUserByLogin(req.getParameter("login"));
        //String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();


        if (user == null || !password.equals(user.getPassword())) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            pageVars.put("message", "Unauthorized. Неправильная пара логин/пароль");
        } else if (password.equals(user.getPassword())) {
            dbService.addSessionId(session.getId(), user);
            pageVars.put("message", "Авторизация успешна. Добро пожаловать!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        resp.getWriter().println(PageGenerator.getInstance().getPage("inform.html", pageVars));
    }
}

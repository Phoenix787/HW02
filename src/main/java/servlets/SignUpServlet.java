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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVars = new HashMap<>();
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(PageGenerator.getInstance().getPage("registration.html", pageVars));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVars = new HashMap<>();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String sessionId = req.getSession().getId();
       if (dbService.getUserByLogin(login) == null) {
            UserDataSet user = new UserDataSet(login, password);
            long id = dbService.addUser(user);
            dbService.addSessionId(sessionId, user);
            pageVars.put("message", "Вы успешно зарегистрированы!");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/html;charset=utf-8");
        }
        else {
            pageVars.put("message", "Пользователь с таким логином уже есть.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/html;charset=utf-8");
        }

        resp.getWriter().println(PageGenerator.getInstance().getPage("inform.html", pageVars));



    }
}


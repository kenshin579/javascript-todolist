package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import svc.TodoBiz;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        log.info("id: " + id + " passwd: " + passwd);

        TodoBiz todoBiz = new TodoBiz();
        User user = todoBiz.getUserLogin(id, passwd);

        if (user != null) {
            log.info("login successful");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // db access

            session.setAttribute("test", "frank");
            response.sendRedirect("todo.html");
//            request.setAttribute("test2", "test2");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("todo.html");
//            dispatcher.forward(request, response);

        } else {
            log.info("login unsuccessful");
//            response.sendRedirect("");
        }

    }

}

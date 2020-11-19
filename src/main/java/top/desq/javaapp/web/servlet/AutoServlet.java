package top.desq.javaapp.web.servlet;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import top.desq.javaapp.web.AutoController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoServlet extends HttpServlet {

    private AutoController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(AutoController.class);
    }

    /*@Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("autoList", controller.getAll());
        req.getRequestDispatcher("/autoPlain.jsp").forward(req, resp);
    }
}

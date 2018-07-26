package com.canvas.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet{
    @Override
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("/login.do".equals(req.getServletPath())){
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(username != null && !"".equals(username.trim())&& username.equals(password)){
                req.getSession().setAttribute("username", username);
                req.getRequestDispatcher("/canvas/list.do").forward(req, resp);
            } else {
                req.getRequestDispatcher("/loginPrompt.do").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }
    }
}

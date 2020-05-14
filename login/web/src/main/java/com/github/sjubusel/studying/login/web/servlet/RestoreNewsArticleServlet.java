package com.github.sjubusel.studying.login.web.servlet;

import com.github.sjubusel.studying.login.service.NewArticleService;
import com.github.sjubusel.studying.login.service.implementation.DefNewsArticleService;
import com.github.sjubusel.studying.login.web.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/restoreArticle"})
public class RestoreNewsArticleServlet extends HttpServlet {
    private NewArticleService service = DefNewsArticleService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("deletedId");
        String idToRestore = req.getParameter("idToRestore");
        if (service.restoreArticleById(idToRestore)) {
            req.getSession().setAttribute("restoreMessage",
                    String.format("NewsArticle №%s is successfully restored", idToRestore));
        } else {
            req.getSession().setAttribute("restoreMessage",
                    String.format("A restoration of an article №%s is failed." +
                            "Please, contact an administrator and save an aforementioned id.", idToRestore));
        }
        req.getSession().setAttribute("restoredId", idToRestore);
        Util.sendRedirect("resources", req, resp);
    }
}

package com.github.sjubusel.studying.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Util {
    private Util() {
    }

    public static void forwardToJsp(String jspName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/" + jspName + ".jsp").forward(request, response);
    }
}

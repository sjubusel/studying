package com.github.sjubusel.studying.login;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Util {
    private Util() {
    }

    public static void forwardToJsp(String jspName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/" + jspName + ".jsp").forward(request, response);
    }

    public static void sendRedirect(String servletOrJsp, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/" + servletOrJsp);
    }

    public static String getDateTimeBeBy(HttpServletRequest request) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
                .withLocale(request.getLocale());
        return ZonedDateTime.now().format(dateTimeFormatter);
    }

    public static Cookie getCookieIfExists(Cookie[] cookies, String cookieName) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie;
            }
        }
        return null;
    }

    // FIXME: need connection to service module
    public static boolean ifCookieIsValid(Cookie cookie) {
//        HashSet<User> authUsers = Storage.getAuthUsers();
//        String identifier = cookie.getValue();
//        for (User authUser : authUsers) {
//            if (authUser.getId().equals(identifier)) {
//                return true;
//            }
//        }
        return false;
    }
}

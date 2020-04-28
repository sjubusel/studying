package com.github.sjubusel.studying.login;

import com.github.sjubusel.studying.login.implementation.DefAuthUserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Util {
    private static AuthUserService authUserService = DefAuthUserService.getInstance();

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

    public static String getDateTime(Object language) {
        String[] localeParts = ((String) language).split("_");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
                .withLocale(new Locale(localeParts[0], localeParts[1]));
        return ZonedDateTime.now().format(dateTimeFormatter);
    }

    public static String getZonedDateTimeToString(ZonedDateTime zonedDateTime, Locale locale) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
                .withLocale(locale);
        return zonedDateTime.format(dateTimeFormatter);
    }

    public static Cookie createLongTimeCookie(String value) {
        Cookie cookie = new Cookie("logged", value);
        cookie.setMaxAge(24 * 60 * 60);
        return cookie;
    }

    public static Cookie getCookieIfExists(String cookieName, Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static boolean ifCookieIsValid(Cookie cookie) {
        return authUserService.verifyUserAuthenticity(cookie.getValue());
    }
}

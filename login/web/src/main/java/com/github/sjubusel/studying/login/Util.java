package com.github.sjubusel.studying.login;

import javax.servlet.ServletException;
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

    public static String getDateTimeBeBy(HttpServletRequest request) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
                .withLocale(request.getLocale());
        return ZonedDateTime.now().format(dateTimeFormatter);
    }
}

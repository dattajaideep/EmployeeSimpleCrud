package com.learn.employees.helper;

//import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebUtils {
    private static HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        WebUtils.request = request;
    }

    public String getClientIp() {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}

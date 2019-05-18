package com.yinfeixing.utils.web;

import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.security.SecurityCode;
import com.yinfeixing.utils.security.SecurityHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebHelper
 *
 * @author liwei
 * @date 16/8/4
 * @description Web常用方法帮助类
 */
public class WebHelper {
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookie = request.getCookies();
        String cookieValue = null;
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals(cookieName) == true) {
                    cookieValue = cookie[i].getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String value) {
        setCookie(response, cookieName, value, null);
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String value, String domain) {
        setCookie(response, cookieName, value, null, domain, null);
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String value, int expiry) {
        setCookie(response, cookieName, value, expiry, null, null);
    }

    public static void delCookie(HttpServletResponse response, String cookieName) throws IOException {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
//		flushbuffer() 用此方法会自动提交响应 导致写入状态代码和标题
//		response.flushBuffer();
    }

    public static void setCookie(HttpServletResponse response, String cookieName, String value, Integer expiry, String domain, String path) {
        try {
            Cookie cookie;
            cookie = new Cookie(cookieName, URLEncoder.encode(value, "UTF-8"));
            if (expiry != null) {
                cookie.setMaxAge(expiry);
            }
            if (domain != null && !domain.equals("")) {
                if (!domain.startsWith(".")) {
                    domain = "." + domain;
                }
                cookie.setDomain(domain);
            }
            if (path != null) {
                cookie.setPath(path);
            } else {
                cookie.setPath("/");
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    /*    */

    /**
     * @param request
     * @return
     * @Title: getUseAgent
     * @Description: 获取useragent
     *//*
    public static String getUseAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        return userAgent;
    }*/
    /*
     * 获取客户端浏览器类型tring
     * */
    public static String getUseAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            if (userAgent.contains("Chrome")) {
                /**
                 * ***********
                 * Chrome 系列
                 * ***********
                 * Chrome 24.0.1295.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.15 (KHTML, like Gecko) Chrome/24.0.1295.0 Safari/537.15
                 * Chrome 24.0.1292.0   -   Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1292.0 Safari/537.14
                 * Chrome 24.0.1290.1   -   Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13
                 * 判断依据:http://www.useragentstring.com/pages/Chrome/
                 */
                String temp = userAgent.substring(userAgent.indexOf("Chrome/") + 7);//拿到User Agent String "Chrome/" 之后的字符串,结果形如"24.0.1295.0 Safari/537.15"或"24.0.1295.0"
                String chromeVersion = null;
                if (temp.indexOf(" ") < 0) {//temp形如"24.0.1295.0"
                    chromeVersion = temp;
                } else {//temp形如"24.0.1295.0 Safari/537.15"
                    chromeVersion = temp.substring(0, temp.indexOf(" "));
                }
                return "Chrome" + chromeVersion;
            } else if (userAgent.contains("Firefox")) {
                /**
                 * *******
                 * FF 系列
                 * *******
                 * Firefox 16.0.1   -   Mozilla/5.0 (Windows NT 6.2; Win64; x64; rv:16.0.1) Gecko/20121011 Firefox/16.0.1
                 * Firefox 15.0a2   -   Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2
                 * Firefox 15.0.2   -   Mozilla/5.0 (Windows NT 6.2; WOW64; rv:15.0) Gecko/20120910144328 Firefox/15.0.2
                 * 判断依据:http://www.useragentstring.com/pages/Firefox/
                 */
                String temp = userAgent.substring(userAgent.indexOf("Firefox/") + 8);//拿到User Agent String "Firefox/" 之后的字符串,结果形如"16.0.1 Gecko/20121011"或"16.0.1"
                String ffVersion = null;
                if (temp.indexOf(" ") < 0) {//temp形如"16.0.1"
                    ffVersion = temp;
                } else {//temp形如"16.0.1 Gecko/20121011"
                    ffVersion = temp.substring(0, temp.indexOf(" "));
                }
                return "Firefox" + ffVersion;
            } else if (userAgent.contains("MSIE") || (userAgent.contains("like Gecko") && userAgent.contains("Windows"))) {
                /**
                 * *******
                 * IE 系列
                 * *******
                 * MSIE 10.0    -   Internet Explorer 10
                 * MSIE 9.0 -   Internet Explorer 9
                 * MSIE 8.0 -   Internet Explorer 8 or IE8 Compatibility View/Browser Mode
                 * MSIE 7.0 -   Windows Internet Explorer 7 or IE7 Compatibility View/Browser Mode
                 * MSIE 6.0 -   Microsoft Internet Explorer 6
                 * 判断依据:http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx
                 */
                if (userAgent.contains("MSIE 10.0")) {//Internet Explorer 10
                    return "Internet Explorer" + "10";
                } else if (userAgent.contains("MSIE 9.0")) {//Internet Explorer 9
                    return "Internet Explorer" + "9";
                } else if (userAgent.contains("MSIE 8.0")) {//Internet Explorer 8
                    return "Internet Explorer" + "8";
                } else if (userAgent.contains("MSIE 7.0")) {//Internet Explorer 7
                    return "Internet Explorer" + "7";
                } else if (userAgent.contains("MSIE 6.0")) {//Internet Explorer 6
                    return "Internet Explorer" + "6";
                } else if (userAgent.contains("like Gecko")) {
                    return "Internet Explorer" + "11";
                }
            } else if (userAgent.contains("Safari") && !userAgent.contains("Android") && !userAgent.contains("iPhone") && !userAgent.contains("iPad")) {
                String temp = userAgent.substring(userAgent.indexOf("Safari/") + 8);//拿到User Agent String "Firefox/" 之后的字符串,结果形如"16.0.1 Gecko/20121011"或"16.0.1"
                String version = null;
                if (temp.indexOf(" ") < 0) {
                    version = temp;
                } else {
                    version = temp.substring(0, temp.indexOf(" "));
                }
                return "Safari" + version;
            } else if (userAgent.contains("Opera")) {
                String temp = userAgent.substring(userAgent.indexOf("Opera/") + 8);//拿到User Agent String "Firefox/" 之后的字符串,结果形如"16.0.1 Gecko/20121011"或"16.0.1"
                String version = null;
                if (temp.indexOf(" ") < 0) {
                    version = temp;
                } else {
                    version = temp.substring(0, temp.indexOf(" "));
                }
                return "Opera" + version;
            }

            if (userAgent.contains("AppleWebKit")) {
                if (userAgent.contains("iPhone") || userAgent.contains("iphone")) {
                    return "iosHtml5";
                }

                if (userAgent.contains("iPad") || userAgent.contains("ipad")) {
                    return "iPadHtml5";
                }

                if (userAgent.contains("Android") || userAgent.contains("android")) {
                    return "androidHtml5";
                }
            }

            if (userAgent.contains("iPhone") || userAgent.contains("iphone")) {
                return "iosApp";
            }

            if (userAgent.contains("iPad") || userAgent.contains("ipad")) {
                return "iPadApp";
            }

            if (userAgent.contains("Android") || userAgent.contains("android")) {
                return "androidApp";
            }
        }
        return "";
    }

    public static boolean getUseAgent(HttpServletRequest request, String system) {
        boolean bl = false;//iPhone Android
        String userAgent = request.getHeader("user-agent");
        if (userAgent.contains(system)) {
            bl = true;
        }
        return bl;
    }

    /**
     * 过滤脚本注入
     */
    public static String encode(String value) {
        if (value != null) {
            return value.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;").replaceAll("'", "&acute;");
        } else {
            return null;
        }
    }

    public static Boolean isSpider(HttpServletRequest request) {
        boolean isSpider = false;
        String[] spiderAgents = {"baiduspider", "googlebot", "bingbot", "haosouspider"};
        if (request.getHeader("User-Agent") != null) {
            for (String spiderAgent : spiderAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(spiderAgent) >= 0) {
                    isSpider = true;
                    break;
                }
            }
        }
        return isSpider;
    }

    /**
     * 获取客户端真实ip地址
     *
     * @return
     */
    public static String getRemoteAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        //区分大小写的情况
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        //阿里云盾
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_REAL_IP");
        }

        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")
                    || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = "获取不到客户端IP请检查网络配置";
        }
        return ipAddress;
    }

    /**
     * 查询session中是否存在uuid如果存在则不做处理，如果不存在则查看cookies中是否存在，如果cookies中不存在则添加一个新的到cookies和session中
     *
     * @param request
     * @param response
     */
    public static void checkUuid(HttpServletRequest request, HttpServletResponse response, String uName) {
        try {
            String uuid = (String) request.getSession().getAttribute(uName);
            if (uuid == null || "".equals(uuid)) {
                //从cookie中读取
                uuid = getCookie(request, uName);
                if (uuid == null || "".equals(uuid)) {
                    //重新生成新的uuid
                    uuid = uName + SecurityHelper.MD5(SecurityCode.getSecurityCode(16, SecurityCode.SecurityCodeLevel.Medium, true) + System.currentTimeMillis()).toLowerCase();
                    //写入cookie
                    setCookie(response, uName, uuid, 315360000, null, null);
                }
                //写入session
                request.getSession().setAttribute(uName, uuid);
            }
        } catch (Exception e) {
            LogHelper.error(e);
        }
    }

    /**
     * 提取主域名
     *
     * @param url
     * @return
     * @throws MalformedURLException
     */
    public static String getTopDomainWithoutSubdomain(String url) throws MalformedURLException {
        String s = "[^(\\.|www)]+\\.((com|xin|shop|club|top|wang|win|site|vip|store|net|bid|cc|ltd|ren|lol|mom|online|tech|biz|red|website|space|link|news|date|loan|mobi|press|video|market|live|studio|help|info|click|pics|photo|trade|vc|party|rocks|band|gift|wiki|design|software|social|lawyer|engineer|org|com|net|org|gov|name|tv|me|asia|co|work|game|games|pro|kim|science|group|pub|pw|men|house)(\\.\\w{2})?|\\w{2})$";
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 获取是否为AJAX请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.hasText(requestType)) {
            return true;
        }
        return false;
    }
}

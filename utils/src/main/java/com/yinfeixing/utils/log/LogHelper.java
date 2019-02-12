package com.yinfeixing.utils.log;

import com.yinfeixing.utils.string.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogHelper {
    private static final transient Logger logger = LogManager.getLogger(LogHelper.class);
    private static Pattern pattern = Pattern.compile("(?<=\\【)[^\\】]+");

    public LogHelper() {
    }

    private static void initFlag(String message) {
        if (!StringUtil.isEmpty(message)) {
            try {
                Matcher matcher = pattern.matcher(message);
                StringBuilder flags = new StringBuilder();

                while(matcher.find()) {
                    flags.append(matcher.group()).append(" ");
                    if (flags.length() > 64) {
                        break;
                    }
                }

                ThreadContext.put("flag", flags.toString());
            } catch (Exception var3) {
                ;
            }

        }
    }

    public static String getMessage(String template, Object... parameters) {
        try {
            initFlag(template);
            return MessageFormat.format(template, parameters);
        } catch (Exception var3) {
            return template + "【日志打印异常】" + var3.getMessage();
        }
    }

    public static void debug(Logger logger, String template, Object... parameters) {
        if (logger.isDebugEnabled()) {
            logger.debug(getMessage(template, parameters));
        }

    }

    public static void info(Logger logger, String template, Object... parameters) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(template, parameters));
        }

    }

    public static void warn(Logger logger, String template, Object... parameters) {
        if (logger.isWarnEnabled()) {
            logger.warn(getMessage(template, parameters));
        }

    }

    public static void error(Logger logger, String template, Object... parameters) {
        if (logger.isErrorEnabled()) {
            logger.error(getMessage(template, parameters));
        }

    }

    public static void exception(Throwable e, Logger logger, String template, Object... parameters) {
        logger.error(getMessage(template, parameters), e);
    }

    public static void error(Object message) {
        logger.error(message);
    }

    public static void error(Object message, Throwable e) {
        logger.error(message, e);
    }

    public static void debug(Object message) {
        logger.debug(message);
    }

    public static void debug(Object message, Throwable e) {
        logger.debug(message, e);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void info(Object message, Throwable e) {
        logger.info(message, e);
    }

    public static void fatal(Object message) {
        logger.fatal(message);
    }

    public static void fatal(Object message, Throwable e) {
        logger.fatal(message, e);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void warn(Object message, Throwable e) {
        logger.warn(message, e);
    }
}

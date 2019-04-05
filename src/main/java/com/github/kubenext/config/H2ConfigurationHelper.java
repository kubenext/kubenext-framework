package com.github.kubenext.config;


import org.h2.tools.Server;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * 在开发中配置H2数据库
 *
 * @author lishangjin
 */
public class H2ConfigurationHelper {

    public static Server createServer() throws SQLException {
        return createServer("9092");
    }

    public static Server createServer(String port) throws SQLException {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Class<?> serverClass = Class.forName("org.h2.tools.Server", true, loader);
            Method createServer = serverClass.getMethod("createTcpServer", String[].class);
            return (Server) createServer.invoke(null, new Object[] {new String[]{"-tcp", "-tcpAllowOthers","-tcpPort", port}});
        } catch (ClassNotFoundException | LinkageError e) {
            throw new RuntimeException("Failed to load and initialize org.h2.tools.Server", e);
        } catch (SecurityException | NoSuchMethodException e) {
            throw new RuntimeException("Failed to get method org.h2.tools.Server.createTcpServer()", e);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to invoke org.h2.tools.Server.createTcpServer()", e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t instanceof SQLException) {
                throw (SQLException) t;
            }
            throw new RuntimeException("Unchecked exception in org.h2.tools.Server.createTcpServer()", t);
        }
    }

    public static void initH2Console(ServletContext servletContext) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Class<?> servletClass = Class.forName("org.h2.server.web.WebServlet", true, loader);
            Servlet servlet = (Servlet) servletClass.getDeclaredConstructor().newInstance();
            ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", servlet);
            h2ConsoleServlet.addMapping("/h2/console/*");
            h2ConsoleServlet.setInitParameter("-properties", "src/main/resources/");
            h2ConsoleServlet.setLoadOnStartup(1);
        } catch (ClassNotFoundException | NoSuchMethodException | LinkageError e) {
            throw new RuntimeException("Failed to load and initialize org.h2.server.web.WebServlet", e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Failed to instantiate org.h2.server.web.WebServlet", e);
        }
    }

}

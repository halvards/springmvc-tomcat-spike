package com.skogsrud.halvard.springmvc.spike.tomcat;

import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

public class Server {
    private static final int DEFAULT_PORT = 8080;
    private final Tomcat tomcat;

    public static void main(String[] args) throws Exception {
        new Server().run();
    }

    public Server() throws Exception {
        this(getPort());
    }

    public Server(int port) throws Exception {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("/app", new File("src/main/webapp").getAbsolutePath());
    }

    /**
     * Start server and block, waiting for it to stop.
     */
    public void run() throws Exception {
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Start server and return when server has started.
     */
    public void start() throws Exception {
        tomcat.start();
    }

    public void stop() throws Exception {
        tomcat.stop();
    }

    private static int getPort() throws IOException {
        return System.getenv("PORT") == null ? DEFAULT_PORT : Integer.parseInt(System.getenv("PORT"));
    }
}

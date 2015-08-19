package com.skogsrud.halvard.springmvc.spike.tomcat;

import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Launch {
    private static final int DEFAULT_PORT = 8080;
    private final Tomcat tomcat;

    public static void main(String[] args) throws Exception {
        new Launch().start();
    }

    public Launch() throws Exception {
        tomcat = new Tomcat();
        tomcat.setPort(getPort());
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("/app", new File("src/main/webapp").getAbsolutePath());
    }

    public void start() throws Exception {
        tomcat.start();
        tomcat.getServer().await();
    }

    public void stop() throws Exception {
        tomcat.stop();
    }

    private int getPort() throws IOException {
        String portEnv = System.getenv("PORT");
        if ("0".equals(portEnv)) {
            return findRandomOpenPort();
        }
        if ((portEnv != null)) {
            return Integer.parseInt(portEnv);
        }
        return DEFAULT_PORT;
    }

    private int findRandomOpenPort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }
}

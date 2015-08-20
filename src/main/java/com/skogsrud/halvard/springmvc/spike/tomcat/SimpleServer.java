package com.skogsrud.halvard.springmvc.spike.tomcat;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * A simpler alternative to the Server class.
 */
public class SimpleServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("/app", new File(System.getProperty("java.io.tmpdir")).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}

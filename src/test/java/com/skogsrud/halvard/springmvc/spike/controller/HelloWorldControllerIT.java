package com.skogsrud.halvard.springmvc.spike.controller;

import com.skogsrud.halvard.springmvc.spike.tomcat.Server;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloWorldControllerIT {
    private static Server server;
    private static int port;
    private static OkHttpClient client;

    @BeforeClass
    public static void beforeClass() throws Exception {
        port = findRandomOpenPort();
        server = new Server(port);
        server.start();
        client = new OkHttpClient();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stop();
    }

    @Test
    public void testHelloWorld() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:" + port + "/app/hello")
                .build();
        Response response = client.newCall(request).execute();
        assertThat(response.code(), equalTo(200));
        assertThat(response.body().string(), equalTo("Hello world"));
    }

    private static int findRandomOpenPort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }
}

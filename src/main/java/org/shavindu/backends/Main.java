package org.shavindu.backends;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.shavindu.backends.slow.SlowBackendSingleThread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/slow", new SlowBackendSingleThread.SlowHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + port);
    }
}
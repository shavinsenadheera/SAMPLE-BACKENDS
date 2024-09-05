package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/slow", new SlowHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + port);
    }

    static class SlowHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                // Simulate slow processing
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Request received................!");

            String sampleJson = "{\n" +
                    "            \"data\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"call-detail-records\",\n" +
                    "                    \"id\": \"12345\",\n" +
                    "                    \"attributes\": {\n" +
                    "                        \"event-type\": \"VOICE\",\n" +
                    "                        \"event-at\": \"2024-03-21T10:30:00Z\",\n" +
                    "                        \"billable-amount\": 5.50,\n" +
                    "                        \"duration\": 180,\n" +
                    "                        \"calling-number\": \"+923001234567\",\n" +
                    "                        \"called-number\": \"+923009876543\",\n" +
                    "                        \"call-direction\": \"OUTGOING\",\n" +
                    "                        \"roaming\": false,\n" +
                    "                        \"call-destination\": \"onnet\",\n" +
                    "                        \"balances\": [\n" +
                    "                            {\n" +
                    "                                \"balance-type\": \"MAIN_ACCOUNT\",\n" +
                    "                                \"transaction-amount\": 5.50,\n" +
                    "                                \"balance-after\": 94.50\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"type\": \"call-detail-records\",\n" +
                    "                    \"id\": \"67890\",\n" +
                    "                    \"attributes\": {\n" +
                    "                        \"event-type\": \"SMS\",\n" +
                    "                        \"event-at\": \"2024-03-21T11:15:00Z\",\n" +
                    "                        \"billable-amount\": 1.00,\n" +
                    "                        \"duration\": null,\n" +
                    "                        \"calling-number\": \"+923001234567\",\n" +
                    "                        \"called-number\": \"+923009876543\",\n" +
                    "                        \"call-direction\": \"OUTGOING\",\n" +
                    "                        \"roaming\": false,\n" +
                    "                        \"call-destination\": \"offnet\",\n" +
                    "                        \"balances\": [\n" +
                    "                            {\n" +
                    "                                \"balance-type\": \"SMS_BUNDLE\",\n" +
                    "                                \"transaction-amount\": 1,\n" +
                    "                                \"balance-after\": 49\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"type\": \"call-detail-records\",\n" +
                    "                    \"id\": \"13579\",\n" +
                    "                    \"attributes\": {\n" +
                    "                        \"event-type\": \"DATA\",\n" +
                    "                        \"event-at\": \"2024-03-21T12:00:00Z\",\n" +
                    "                        \"billable-amount\": 10.00,\n" +
                    "                        \"duration\": 3600,\n" +
                    "                        \"calling-number\": \"+923001234567\",\n" +
                    "                        \"called-number\": null,\n" +
                    "                        \"call-direction\": null,\n" +
                    "                        \"roaming\": false,\n" +
                    "                        \"call-destination\": null,\n" +
                    "                        \"balances\": [\n" +
                    "                            {\n" +
                    "                                \"balance-type\": \"DATA_BUNDLE\",\n" +
                    "                                \"transaction-amount\": 100,\n" +
                    "                                \"balance-after\": 900\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }";


            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, sampleJson.length());
            OutputStream os = exchange.getResponseBody();
            os.write(sampleJson.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
    }
}
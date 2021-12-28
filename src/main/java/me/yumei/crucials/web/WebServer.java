package me.yumei.crucials.web;

import com.sun.net.httpserver.HttpServer;
import me.yumei.crucials.web.handlers.RootHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WebServer {

    static int port = 8803;
    static String hostname = "localhost";
    static HttpServer server;

    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void setupServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(hostname, port), 0);

        server.createContext("/", new RootHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();

        System.out.println("[Crucials] Web server started on port " + port);
    }
}

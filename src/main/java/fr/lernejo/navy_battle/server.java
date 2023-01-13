package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class server {

    private final HttpServer server;
    public server(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.createContext("/ping", new Ping());
        server.createContext("/api/game/start", new Start(port));
    }
    public void start(){
        server.start();
    }
}

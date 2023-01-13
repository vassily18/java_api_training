package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0 || args.length >= 3)
            throw new IllegalArgumentException("Bad Arguments");
        int port = Integer.parseInt(args[0]);
        server serv = new server(port);
        serv.start();
        if (args.length == 2){
            request_post p = new request_post(args);
            p.Post_Rq();
        }
    }
}

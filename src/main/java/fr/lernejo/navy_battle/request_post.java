package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.*;

public class request_post {
    private final String[] args;

    public request_post(String[] _args){
        args = _args;
    }

    public void Post_Rq() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String adversaryUrl = args[1];
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + args[0] + "\", \"message\":\"hola  \"}"))
            .build();
        HttpResponse<String> res = client.send(request, BodyHandlers.ofString());
    }

}

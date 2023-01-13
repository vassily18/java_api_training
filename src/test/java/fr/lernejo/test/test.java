package fr.lernejo.test;
import fr.lernejo.navy_battle.server;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class test {
    
    @Test
    public void PingTest() throws IOException {
        server s = new server(9876);
        s.start();
        URL url = new URL("http://localhost:9876/ping");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        assertEquals(200, con.getResponseCode());
        String responseBody = new BufferedReader(new InputStreamReader(con.getInputStream()))
            .lines().collect(Collectors.joining("\n"));
        assertEquals("OK",responseBody);
    }
}

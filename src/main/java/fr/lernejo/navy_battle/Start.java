package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Start implements HttpHandler {
    private final int port;
    public Start(int _port){
        port = _port;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(!exchange.getRequestMethod().equals("POST"))
           SendResponse(exchange,404,"Not Found");
        InputStreamReader requestBodyReader = new InputStreamReader(exchange.getRequestBody());
        StringBuilder requestBody = new StringBuilder();
        if(!IsPatternMatching(requestBodyReader,requestBody))
           SendResponse(exchange,400,"Bad Request");
        else{
            JSONObject json = new JSONObject(requestBody.toString());
            json.put("id","9");
            json.put("url","http://localhost:" + port);
            json.put("message", "Hola chico");
            String message = json.toString();
            SendResponse(exchange,202,json.toString());
            System.out.println(json);
        }
    }

    public boolean IsPatternMatching(InputStreamReader requestBodyReader,StringBuilder requestBody) throws IOException {
        int c;
        while ((c = requestBodyReader.read()) != -1)
            requestBody.append((char) c);
        String s = requestBody.toString();
        System.out.println(s);
        return s.contains("id") && s.contains("url") && s.contains("message");
    }
    public void SendResponse(HttpExchange exchange,int code, String message) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}

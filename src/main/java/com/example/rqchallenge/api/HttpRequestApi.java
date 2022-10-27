package com.example.rqchallenge.api;

import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpRequestApi {

    public String httpRequestGetAllEmployees() throws IOException, URISyntaxException, InterruptedException {
        String urlString = "http://dummy.restapiexample.com/api/v1/employees";
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .build();

        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

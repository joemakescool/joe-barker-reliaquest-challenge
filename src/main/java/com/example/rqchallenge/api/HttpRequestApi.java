package com.example.rqchallenge.api;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Creates API requests with URIs
 */
@Service
public class HttpRequestApi {

    public String httpRequestGetAllEmployees() throws IOException, URISyntaxException, InterruptedException {
        String urlString = APIConstants.BASE_URL + "/employees";
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .build();

        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String httpRequestGetEmployeeById(String id) throws URISyntaxException, IOException, InterruptedException {
        String urlString = APIConstants.BASE_URL + "/employee/" + id;
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .build();

        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String httpRequestCreateEmployee(String jsonObject) throws URISyntaxException, IOException, InterruptedException {

        String urlString = APIConstants.BASE_URL + "/create";
        HttpClient httpClient = HttpClient.newHttpClient(); // sends out the request

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonObject))
                .build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        return postResponse.body();
    }


    public void httpRequestDeleteEmployee() throws URISyntaxException, IOException, InterruptedException {
        String urlString = APIConstants.BASE_URL + "/delete/4710";

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .GET()
                .build();
        // send the request
        httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
    }
}

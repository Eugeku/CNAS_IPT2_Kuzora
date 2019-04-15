package by.bsuir.main;

import by.bsuir.http.client.HttpClient;
import by.bsuir.http.client.enums.MethodValues;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.createHttpConnection("https://httpbin.org/put", MethodValues.PUT);
        httpClient.settingTimeOuts();
        httpClient.settingRequestHeaders();
        System.out.println(httpClient.getFullResponse());
    }
}

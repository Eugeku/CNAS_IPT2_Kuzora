package by.bsuir.http.client;

import by.bsuir.http.client.enums.MethodValues;
import by.bsuir.http.utils.FullResponseBuilder;
import by.bsuir.http.utils.ParameterStringBuilder;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClient {

    private HttpURLConnection con;
    private List<Exception> listOfConErrors = new ArrayList<>();

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public void createHttpConnection(String url, MethodValues methodValue) {
        try {
            URL urlObject = new URL(url);
            listOfConErrors.clear();
            con = (HttpURLConnection) urlObject.openConnection();
            con.setRequestMethod(methodValue.getValue());
        } catch (IOException ex1) {
            listOfConErrors.add(ex1);
        }
    }

    public boolean addingRequestParameters(Map<String, String> map) {
        boolean flag = !map.isEmpty();
        try {
            if (flag) {
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(map));
                out.flush();
                out.close();
            }
        } catch (IOException ex1) {
            listOfConErrors.add((ex1));
        }
        return flag;
    }

    public boolean settingRequestHeaders(Map<String, String> map) {
        boolean flag = !map.isEmpty();
        if (flag) {
            map.forEach((k, v) -> con.setRequestProperty(k, v));
        }
        return flag;
    }

    public String gettingRequestHeaders(String key) {
        return con.getHeaderField(key);
    }

    public String getFullResponse() {
        return FullResponseBuilder.getFullResponse(con, listOfConErrors);
    }

    public void settingTimeOuts(int connectionTimeout, int readTimeout) {
        con.setConnectTimeout(connectionTimeout);
        con.setReadTimeout(readTimeout);
    }

}

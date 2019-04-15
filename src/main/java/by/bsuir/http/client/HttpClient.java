package by.bsuir.http.client;

import by.bsuir.http.client.enums.MethodValues;
import by.bsuir.http.utils.FullResponseBuilder;
import by.bsuir.http.utils.ParameterStringBuilder;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClient {

    private HttpURLConnection con;

    public HttpURLConnection getCon() {
        return con;
    }

    public void setCon(HttpURLConnection con) {
        this.con = con;
    }

    public void createHttpConnection(String url, MethodValues methodValue) throws Exception {
        URL urlObject = new URL(url);
        con = (HttpURLConnection) urlObject.openConnection();
        con.setRequestMethod(methodValue.getValue());
    }

    public void addingRequestParameters(Map<String, String> map) throws Exception {
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(map));
        out.flush();
        out.close();
    }

    public void settingRequestHeaders(Map<String, String> map) {
        map.forEach((k, v)-> con.setRequestProperty(k, v));
    }

    public String gettingRequestHeaders(String key) {
        return con.getHeaderField(key);
    }

    public String getFullResponse() throws Exception {
        return FullResponseBuilder.getFullResponse(con);
    }

    public void settingTimeOuts(int connectionTimeout, int readTimeout) {
        con.setConnectTimeout(connectionTimeout);
        con.setReadTimeout(readTimeout);
    }

}

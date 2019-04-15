package by.bsuir.http.client;


import by.bsuir.http.client.enums.MethodValues;
import by.bsuir.http.utils.FullResponseBuilder;
import by.bsuir.http.utils.ParameterStringBuilder;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private HttpURLConnection con;

    public void createHttpConnection(String url, MethodValues methodValue) throws Exception {
        URL urlObject = new URL(url);
        con = (HttpURLConnection) urlObject.openConnection();
        con.setRequestMethod(methodValue.getValue());
    }

    public void addingRequestParameters(String key, String value) throws Exception {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(key, value);
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();
    }

    public void settingRequestHeaders() {
        con.setRequestProperty("Accept", "application/json");
    }

    public String gettingRequestHeaders() {
        return con.getHeaderField("Content-Type");
    }

    public String getFullResponse() throws Exception {
        return FullResponseBuilder.getFullResponse(con);
    }

    public void settingTimeOuts() {
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
    }

}

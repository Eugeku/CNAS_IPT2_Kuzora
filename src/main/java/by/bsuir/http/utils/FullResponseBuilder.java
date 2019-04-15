package by.bsuir.http.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;

public class FullResponseBuilder {

    private static final String BLANK = " ";
    private static final String NEW_LINE = "\n";
    private static final String COLON_SIGN = ": ";
    private static final String COMMA_SIGN = ", ";

    public static String getFullResponse(HttpURLConnection con) throws IOException {
        StringBuilder fullResponseBuilder = new StringBuilder();

        // read status and message
        fullResponseBuilder.append(con.getResponseCode())
                .append(BLANK)
                .append(con.getResponseMessage())
                .append(NEW_LINE);

        // read headers
        con.getHeaderFields().entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .forEach(entry -> {
                    fullResponseBuilder.append(entry.getKey()).append(COLON_SIGN);
                    List headerValues = entry.getValue();
                    Iterator it = headerValues.iterator();
                    if (it.hasNext()) {
                        fullResponseBuilder.append(it.next());
                        while (it.hasNext()) {
                            fullResponseBuilder.append(COMMA_SIGN).append(it.next());
                        }
                    }
                    fullResponseBuilder.append(NEW_LINE);
                });

        // read response content
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            fullResponseBuilder.append(inputLine);
        }
        in.close();
        return fullResponseBuilder.toString();
    }
}

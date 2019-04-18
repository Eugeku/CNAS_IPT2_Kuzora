package by.bsuir.http.client.enums;

public enum MethodValues {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PUT("PUT"),
    DELETE("DELETE")
    ;

    String value;

    MethodValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

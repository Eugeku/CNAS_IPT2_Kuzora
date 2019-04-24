package by.bsuir.controller;

import by.bsuir.http.client.HttpClient;
import by.bsuir.http.client.enums.MethodValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private static final String defaultBodyMessage = "Header parameters:\n%s\n_____________________\nRequest parameters:\n%s\n_____________________";
    private HttpClient httpClient = new HttpClient();
    private Map<String, String> parRequestHeaders = new HashMap<>();
    private Map<String, String> parRequestParameters = new HashMap<>();
    private ObservableList<MethodValues> methods = FXCollections.observableArrayList(MethodValues.values());

    @FXML
    private ComboBox<MethodValues> comboBox1 = new ComboBox<MethodValues>(methods);

    @FXML
    private Button button1, button2, button3, button4;

    @FXML
    private TextField textField1, textField2, textField3, textField4, textField5;

    @FXML
    private TextArea textArea1, textArea2;

    public void initMethodValues(Event event) {
        comboBox1.setItems(methods);
        comboBox1.setValue(methods.get(0));
        button1.setDisable(false);
        button2.setDisable(false);
        button3.setDisable(false);
        button4.setDisable(false);
        comboBox1.setDisable(false);
        textField2.setDisable(false);
        textField3.setDisable(false);
        textField4.setDisable(false);
        textField5.setDisable(false);
    }

    public void setButton1(ActionEvent event) {
        comboBox1.setItems(methods);
        httpClient.createHttpConnection(textField1.getText(), comboBox1.getValue());
        httpClient.settingTimeOuts(5000, 5000);
        textArea1.clear();
        httpClient.settingRequestHeaders(parRequestHeaders);
        httpClient.addingRequestParameters(parRequestParameters);
        textArea1.setText(httpClient.getFullResponse());
    }

    public void setButton2(ActionEvent event) {
        String key = textField2.getText();
        String value = textField3.getText();
        if (!key.isEmpty() && !value.isEmpty()) {
            parRequestHeaders.put(key, value);
            textField2.clear();
            textField3.clear();
            setTextAreaText();
        } else {
        }
    }

    public void setButton3(ActionEvent event) {
        String key = textField4.getText();
        String value = textField5.getText();
        if (!key.isEmpty() && !value.isEmpty()) {
            parRequestParameters.put(key, value);
            textField4.clear();
            textField5.clear();
            setTextAreaText();
        } else {
        }
    }

    public void setButton4(ActionEvent event) {
        parRequestHeaders.clear();
        parRequestParameters.clear();
        setTextAreaText();
    }

    private void setTextAreaText() {
        textArea2.setText(String.format(defaultBodyMessage, parRequestHeaders, parRequestParameters));
    }
}

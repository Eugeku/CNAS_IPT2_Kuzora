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

    private ObservableList<MethodValues> methods = FXCollections.observableArrayList(MethodValues.values());

    @FXML
    private ComboBox<MethodValues> comboBox1 = new ComboBox<MethodValues>(methods);

    @FXML
    private Button button1, button2, button3, button4, button5;

    @FXML
    private TextField textField1, textField2;

    @FXML
    private TextArea textArea1, textArea2;

    public void setButton1(ActionEvent event) {
    }

    public void setButton5(ActionEvent event) {
    }

    public void setComboBox2(Event event) {
    }

    public void setButton4(ActionEvent event) {
    }

    public void setButton2(ActionEvent event) throws Exception {

        comboBox1.setItems(methods);
        HttpClient httpClient = new HttpClient();
        httpClient.createHttpConnection(textField1.getText(), comboBox1.getValue());
        httpClient.settingTimeOuts(5000, 5000);
        Map<String, String> map = new HashMap<>();
//        map.put("pop", "Eugene");
//        httpClient.addingRequestParameters(map);

        textArea1.clear();
        textArea2.clear();
        textArea1.setText(httpClient.getFullResponse());
    }

    public void setButton3(ActionEvent event) {
//        saveListToFile("file1.ser");
    }

}

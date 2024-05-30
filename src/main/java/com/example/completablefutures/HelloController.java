package com.example.completablefutures;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label studentText;

    @FXML
    private VBox mainBox;

    @FXML
    protected void onHelloButtonClick() {
        if (welcomeText.getText().equals("")) welcomeText.setText("Welcome to JavaFX Application!");
        else welcomeText.setText("");
    }

    @FXML
    protected void onNewButtonClick() {
        if (studentText.getText().equals("")) studentText.setText("That small GUI program was done by student Aleksander Ontin!");
        else studentText.setText("");
    }

    @FXML
    private Line line;

    @FXML
    private Label messageText;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea outputArea;

    //TODO: new necessary function
    @FXML
    protected void onURLButtonClick() {
        System.out.println("Let's start:");
        outputArea.setText("");
        if (inputField.getText().equals("")) {
            messageText.setText("Bad news! Input is Empty!");
            System.out.println("Input is Empty!");
            return;
        }

        String inputFieldText = inputField.getText(); // Getting text from TextField

        CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(inputFieldText); // Creating a URL
                // The URL object represents an absolute URL.
                // An absolute URL contains all of the information necessary to reach the resource in question.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Creating a HttpURLConnection
                // A URLConnection with support for HTTP-specific features.

                connection.setRequestMethod("GET"); // Sending a GET request

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); // Getting a response to the request
                // BufferedReader reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
                String inputLine; // Creating a string variable inputLine. It will be used to store the string that we will read from the input stream.

                List<String> texts = new ArrayList<>();
                while ((inputLine = in.readLine()) != null) { // Loop reading data from input stream
                    // We read lines until we reach the end of the file
                    texts.add(inputLine + "\n");
                    //outputArea.appendText(inputLine + "\n"); // Each line read is added to the TestArea
                }
                in.close(); // BufferedReader closes

                Platform.runLater(new Runnable() {
                    public void run() {
                        for (String text : texts) {
                            outputArea.appendText(text);
                        }
                        messageText.setText("Good news! The HTML code is successfully displayed in the application!");
                    }
                });
                System.out.println("The HTML code is successfully displayed in the application!");
                return null;

            } catch (MalformedURLException e) { // if will be MalformedURLException
                Platform.runLater(new Runnable() {
                    public void run() {
                        messageText.setText("Bad news! The URL you are trying to use has an incorrect format!");
                    }
                });
                System.out.println("The URL you are trying to use has an incorrect format!");
                System.out.println("Make sure the URL starts with http:// or https:// and does not contain any invalid characters!");
                //e.printStackTrace();
                return null;
            } catch (IOException e) { // if will be IOException
                Platform.runLater(new Runnable() {
                    public void run() {
                        messageText.setText("Bad news! Failed to establish a connection to the server!");
                    }
                });
                System.out.println("Failed to establish a connection to the server!");
                System.out.println("Make sure you have an internet connection!");
                //e.printStackTrace();
                return null;
            }
        }).thenAccept(result -> {
            if (result != null) {
                Platform.runLater(() -> outputArea.appendText(result + "\n"));
            }
        });
    }
}
package com.example.completablefutures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**********
 * Write a small GUI program which allows the user to enter a URL and hit a button.
 * The program will then in the background fetch the HTML code from the URL and display it in the GUI.
 * This should not block the user interface.
 * For fetching the page, supplyAsync is an option. thenAccept could be used to update the GUI.
 *
 *
 * For this work, I used JavaFX.
 * Student: Aleksander Ontin
 *
 * Presentation of JavaFX of University of Tartu:
 *      https://courses.cs.ut.ee/2022/OOP/spring/Main/Lectures?action=download&upname=V19GraafilineKasutajaliides.pdf
 * Information about URL:
 *      https://docs.oracle.com/javase/tutorial/networking/urls/creatingUrls.html
 * Information about HttpURLConnection:
 *      https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html
 * Information about BufferedReader:
 *      https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
 * Thread-safe JavaFX Update:
 *      https://gist.github.com/epes/696c65b32901a7b3a394
 *   example:
 *      Platform.runLater(new Runnable() {
 *           public void run() {
 *                Label.setText("Text here");
 *           }
 *      });
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 660);
        stage.setTitle("Lab07 - CompletableFutures");
        stage.setScene(scene);
        stage.setResizable(false); // Not resize the window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
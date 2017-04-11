/*
 * Copyright (c) 2017 Jordan Stremming
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.operontech.polishnotator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {

	// This should always be the next version after the current
	// example: we are on b2, so next is b3
	final String NEXT_VER = "b2";

	@Override
	public void start(final Stage primaryStage) throws Exception {
		// Use FXML file as scene, use CSS for style
		final Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
		root.getStylesheets().add(getClass().getResource("/main.css").toExternalForm());
		primaryStage.setScene(new Scene(root, 850, 450));

		// Change window properties
		primaryStage.setTitle("Polish Notator BETA");
		primaryStage.setResizable(false);

		// Display the stage
		primaryStage.show();

		if (checkForUpdate()) {
			final Alert alert = new Alert(Alert.AlertType.INFORMATION, "There is a new update available!\nYou should go download it!", ButtonType.OK);
			alert.show();
		}
	}

	public static void main(final String[] args) {
		launch(args);
	}

	public boolean checkForUpdate() {
		try {
			final String UPDATE_URL = "https://bitbucket.org/Techzune/polish-notator/downloads/Polish%20Notator-" + NEXT_VER + ".jar";
			HttpURLConnection.setFollowRedirects(false);
			final HttpURLConnection con = (HttpURLConnection) new URL(UPDATE_URL).openConnection();
			con.setRequestMethod("GET");
			return (con.getResponseCode() != HttpURLConnection.HTTP_NOT_FOUND);
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

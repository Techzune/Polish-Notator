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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URI;

public class Controller {

	@FXML
	private TextField textFieldInput;

	@FXML
	private TextField textFieldOutput;

	@FXML
	protected void handleConvert(final ActionEvent e) {
		textFieldOutput.setText(PolishNotator.convertFromStandard(textFieldInput.getText().replaceAll("\\s", "")));
	}

	@FXML
	protected void actionButtonNot() {
		handleButtonOperators(Operator.NOT);
	}

	@FXML
	protected void actionButtonAnd() {
		handleButtonOperators(Operator.AND);
	}

	@FXML
	protected void actionButtonOr() {
		handleButtonOperators(Operator.OR);
	}

	@FXML
	protected void actionButtonIfThen() {
		handleButtonOperators(Operator.IF_THEN);
	}

	@FXML
	protected void actionButtonOnlyIf() {
		handleButtonOperators(Operator.ONLY_IF);
	}

	@FXML
	protected void showBitBucket() {
		try {
			Desktop.getDesktop().browse(new URI("http://bitbucket.org/Techzune/polish-notator"));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void actionButtonLetter(final ActionEvent e) {
		final Button eButton = (Button) e.getSource();
		if (eButton != null) {
			textFieldInput.replaceSelection(eButton.getText());
		} else {
			System.out.println("ERROR: button doesn't exist");
		}
	}

	private void handleButtonOperators(final Operator op) {
		// Add symbol to textFieldInput
		textFieldInput.replaceSelection(op.getSpacedSymbol());

		// Return focus to textFieldInput from button
		textFieldInput.requestFocus();
		textFieldInput.deselect();
	}
}

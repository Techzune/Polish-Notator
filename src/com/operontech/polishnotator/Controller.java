package com.operontech.polishnotator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	TextField textFieldInput;

	@FXML
	protected void handleConvert(ActionEvent e) {
		System.out.println(e.getSource().toString());
	}

	@FXML
	protected void actionButtonNot(ActionEvent e) {
		addToInput('~');
	}

	@FXML
	protected void actionButtonAnd(ActionEvent e) {
		addToInput('•');
	}

	@FXML
	protected void actionButtonOr(ActionEvent e) {
		addToInput('∨');
	}

	@FXML
	protected void actionButtonIfThen(ActionEvent e) {
		addToInput('⊃');
	}

	@FXML
	protected void actionButtonOnlyIf(ActionEvent e) {
		addToInput('≡');
	}

	private void addToInput(char symbol) {
		textFieldInput.setText(textFieldInput.getText() + symbol);
	}
}

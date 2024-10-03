package com.base.ui.firstscreen;

import java.io.IOException;

import com.base.core.ui.alerts.AlertManager;
import com.base.core.ui.scenes.Controller;
import com.base.core.ui.scenes.manager.UIManager;
import com.base.ui.modalscreen.ModalScreenView;
import com.base.ui.secondscreen.SecondScreenView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;

@org.springframework.stereotype.Controller
public class FirstScreenController extends Controller {
	
	@Override
	protected void initialize() {}

	@Override
	public void initializeComponents() {}

	@Override
	public void initializeFocus() {}

	@FXML
	public void onAction() throws IOException {
		
		UIManager.showView(SecondScreenView.class);
	}

	@FXML
	private void showInformationAlert(ActionEvent event) {

		boolean isAccepted = AlertManager.showAlert("Information Header", "Information Content", AlertType.INFORMATION);
		System.out.println(isAccepted);
	}

	@FXML
	private void showConfirmationAlert(ActionEvent event) {

		boolean isAccepted = AlertManager.showAlert("Confirmation Header", "Confirmation Content",
				AlertType.CONFIRMATION);
		System.out.println(isAccepted);
	}

	@FXML
	private void showWarningAlert(ActionEvent event) {

		boolean isAccepted = AlertManager.showAlert("Warning Header", "Warning Content", AlertType.WARNING);
		System.out.println(isAccepted);
	}

	@FXML
	private void showErrorAlert(ActionEvent event) {

		boolean isAccepted = AlertManager.showAlert("Error Header", "Error Content", AlertType.ERROR);
		System.out.println(isAccepted);
	}
	
	@FXML
    void showNormalView(ActionEvent event) {
		UIManager.showView(SecondScreenView.class);
    }

    @FXML
    void showModalView(ActionEvent event) {
    	UIManager.showView(ModalScreenView.class);
    }

}

package com.base.core.ui.alerts;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertManager {

	private static final Logger log = LoggerFactory.getLogger(AlertManager.class);

	/**
	 * Displays a modal alert with a custom title, header and content.
	 * 
	 * @param title   Title of the alert.
	 * @param header  Header of the alert.
	 * @param content Content of the alert.
	 * 
	 * @return true if 'Accept' is pressed, and false if 'Cancel' is pressed.
	 */
	public static boolean showAlert(String header, String content, AlertType alertType) {
		log.info("showAlert() - Tipo de Alert: " + alertType.name());

		AtomicBoolean isAccepted = new AtomicBoolean();

		Alert alert = new Alert(alertType);
		alert.setTitle("Alert");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.setHeight(300);

		alert.showAndWait().ifPresent(buttonType -> {

			if (buttonType == ButtonType.OK) {
				isAccepted.set(true);
			}

			if (buttonType == ButtonType.CANCEL) {
				isAccepted.set(false);
			}

		});

		return isAccepted.get();
	}

}

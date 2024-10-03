package com.base.ui.modalscreen;

import com.base.core.ui.scenes.modal.ModalController;

import javafx.fxml.FXML;

public class ModalScreenController extends ModalController{

	@Override
	protected void initialize() {}

	@Override
	public void initializeComponents() {}

	@Override
	public void initializeFocus() {}

	@FXML
	public void onActionClose() {
		close();
	}
	
}

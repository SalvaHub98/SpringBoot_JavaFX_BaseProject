package com.base.core.ui.scenes.modal;

import com.base.core.ui.scenes.Controller;

import javafx.stage.Stage;

public abstract class ModalController extends Controller {

	private Stage modalStage;
	
	@Override
	protected abstract void initialize();

	@Override
	public abstract void initializeComponents();

	@Override
	public abstract void initializeFocus();

	public void initModalStage(Stage modalStage) {
		log.info(("initModalStage()"));
		
		this.modalStage = modalStage;
	}
	
	/**
	 * Close the current modal window
	 * */
	protected void close() {
		log.info("close() - Cerrando pantalla modal");
		
		modalStage.close();
	}
	
}
